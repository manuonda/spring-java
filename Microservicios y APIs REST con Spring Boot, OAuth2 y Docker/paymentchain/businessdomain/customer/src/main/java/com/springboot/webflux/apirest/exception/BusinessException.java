package com.springboot.webflux.apirest.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;


// Exception controller Advice

// el uno tiene testing a la parte controller 
// 1 - https://www.youtube.com/watch?v=V5jPILLuRKk
// 2 - https://javadesde0.com/manejando-excepciones-con-la-anotacion-controlleradvice-concepto-de-circuit-breaker-cortocircuito/

@Data
public class BusinessException extends RuntimeException {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 168146093730825084L;

	private String code;
	
	private HttpStatus status;
	
	public BusinessException(String code, HttpStatus status, String message) {
		super(message);
		this.code = code;
		this.status = status;
	}
}
