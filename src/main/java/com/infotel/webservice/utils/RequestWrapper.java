package com.infotel.webservice.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.input.TeeInputStream;

public class RequestWrapper extends HttpServletRequestWrapper {

	private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

	private boolean inputStreamCalled = false;

	public RequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ServletInputStream servletInputStream = RequestWrapper.super.getInputStream();

		inputStreamCalled = true;

		return new ServletInputStream() {
			private TeeInputStream tee = new TeeInputStream(servletInputStream, bos);

			@Override
			public int read() throws IOException {
				return tee.read();
			}

			@Override
			public int read(byte[] b, int off, int len) throws IOException {
				return tee.read(b, off, len);
			}

			@Override
			public int read(byte[] b) throws IOException {
				return tee.read(b);
			}

			@Override
			public boolean isFinished() {
				return servletInputStream.isFinished();
			}

			@Override
			public boolean isReady() {
				return servletInputStream.isReady();
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				servletInputStream.setReadListener(readListener);
			}

			@Override
			public void close() throws IOException {
				super.close();
			}
		};
	}

	public byte[] toByteArray() {
		return bos.toByteArray();
	}

	public boolean isInputStreamCalled() {
		return inputStreamCalled;
	}
}
