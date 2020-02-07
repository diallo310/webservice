package com.infotel.webservice.execption;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infotel.webservice.error.ApiError;

@ControllerAdvice
public class ResponseExceptionHandler  extends ResponseEntityExceptionHandler{
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
		    List<String> errors = new ArrayList<String>(); 
		   errors= ex.getBindingResult().getFieldErrors().stream()
		    	.map(
		    		error-> error.getField()+": "+error.getDefaultMessage()).collect(Collectors.toList());
		   
		   errors.addAll(ex.getBindingResult().getGlobalErrors().stream()
		    	.map(error->error.getObjectName()+": "+error.getDefaultMessage()).collect(Collectors.toList()));
		  
		    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,errors);
		return  handleExceptionInternal(ex, apiError,headers, apiError.getStatus(), request);
	}	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ApiError> handleUserException(UserException ex) {
		ArrayList<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
	    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errors);
	    return new ResponseEntity<ApiError>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(RessourceNotFoundException.class)
	public ResponseEntity<ApiError> handleResourceNotFoundException(RessourceNotFoundException ex) {
		ArrayList<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
	    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, errors);
	    return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
	}
}