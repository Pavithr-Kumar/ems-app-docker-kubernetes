package com.pk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFOundException(ResourceNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = BadRequestException.class)
	public ResponseEntity<String> handleBadRequestException(BadRequestException exception){
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
