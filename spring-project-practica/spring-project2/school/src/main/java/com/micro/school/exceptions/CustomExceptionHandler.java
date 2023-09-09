package com.micro.school.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.ws.rs.NotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handlerNotFound(){
		return null;
	}
}
