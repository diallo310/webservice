package com.infotel.webservice.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.output.TeeOutputStream;

public class ResponseWrapper extends HttpServletResponseWrapper {
		
	private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
	
	public ResponseWrapper(HttpServletResponse response) {
		super(response);
	}
	
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		final ServletOutputStream servletOutputStream = ResponseWrapper.super.getOutputStream();
		return new ServletOutputStream() {
			private TeeOutputStream tee = new TeeOutputStream(servletOutputStream, bos);
			
			@Override
			public void write(byte[] b) throws IOException {
				tee.write(b);
			}
			
			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				tee.write(b, off, len);
			}
			
			@Override
			public void flush() throws IOException {
				tee.flush();
			}
			
			@Override
			public void write(int b) throws IOException {
				tee.write(b);
			}
			
			@Override
			public boolean isReady() {
				return servletOutputStream.isReady();
			}
			
			@Override
			public void setWriteListener(WriteListener writeListener) {
				servletOutputStream.setWriteListener(writeListener);
			}
			
			
			@Override
			public void close() throws IOException {
				super.close();
			}
		};
	}
	
	
	
	/**
	 * this method will clear the buffer, so
	 *
	 * @return captured bytes from stream
	 */
	public byte[] toByteArray() {
		byte[] ret = bos.toByteArray();
		bos.reset();
		return ret;
	}
}
