package com.infotel.webservice.error;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
	private HttpStatus status;
	private List<String> errors;

	/**
	 * 
	 * @param status httpStatus
	 * @param errors list errors
	 */
	public ApiError(HttpStatus status, List<String> errors) {
		this.status = status;
		this.errors = errors;
	}

	/**
	 * 
	 * @return httpStatus
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status HttpStatus of request
	 */

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * 
	 * @return list errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * 
	 * @param errors list errors
	 */

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}