package com.blulogix.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.blulogix.springdemo.models.ErrorResponse;
import com.blulogix.springdemo.models.UserError;

@ControllerAdvice
public class UserRestErrorHandler {

	
@ExceptionHandler
public ResponseEntity <ErrorResponse> handleException(UserError userError){
	return new ResponseEntity<ErrorResponse>( new ErrorResponse(
			HttpStatus.NOT_FOUND.value(),
			userError.getMessage(),
			System.currentTimeMillis()
			),HttpStatus.NOT_FOUND);
	
}

@ExceptionHandler
public ResponseEntity <ErrorResponse> handleException(Exception ex){
	System.out.println(ex.toString());
	return new ResponseEntity<ErrorResponse>( new ErrorResponse(
			HttpStatus.BAD_REQUEST.value(),
			"error",
			System.currentTimeMillis()
			),HttpStatus.BAD_REQUEST);
	
}
}
