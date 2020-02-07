package com.infotel.webservice.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import com.infotel.webservice.utils.RequestWrapper;
import com.infotel.webservice.utils.ResponseWrapper;

@Component
public class LogInterceptor extends AbstractRequestLoggingFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long startTime = System.currentTimeMillis();

		logger.info("Start : " + request.getRequestURL());

		RequestWrapper requestWrapper = new RequestWrapper(request);
		ResponseWrapper responseWrapper = new ResponseWrapper(response);
		logger.info(request.getRequestURI());

		super.doFilterInternal(requestWrapper, responseWrapper, filterChain);

		long endTime = System.currentTimeMillis();

		if (request.getRequestURI().contains("/users/")) {

			if ("POST".equals(request.getMethod())) {
				logger.info("Http request " + new String(requestWrapper.toByteArray()));
			} else {
				logger.info("Http request " + requestWrapper.getQueryString());
			}
			logger.info("Http response " + new String(responseWrapper.toByteArray()));

			logger.info("{} took: {} ms ", request.getRequestURL(), (endTime - startTime));
		}
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {

	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {

	}

}