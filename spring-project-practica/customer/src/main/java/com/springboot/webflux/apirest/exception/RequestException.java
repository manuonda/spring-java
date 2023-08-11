package com.springboot.webflux.apirest.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9221237431386363268L;
	private String code;
	private String message;
	

	public RequestException(String code, String message) {
		super(message);
		this.code = code;
	}

	
	
}
