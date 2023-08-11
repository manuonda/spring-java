package com.springboot.webflux.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.webflux.apirest.dto.ErrorDTO;

@RestControllerAdvice
public class ControllerAdvice {

	
	/*
	 * Definimos la exception a controlar
	 */
	@ExceptionHandler(value =  RuntimeException.class)
	public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex) {
		ErrorDTO error = ErrorDTO.builder().code("P-500").message(ex.getMessage()).build();
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value =  RequestException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex) {
		ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * Capture exception Business Exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value =  BusinessException.class)
	public ResponseEntity<ErrorDTO> businessException(BusinessException ex) {
		ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
		return new ResponseEntity<ErrorDTO>(error, ex.getStatus());
	}
	
}
