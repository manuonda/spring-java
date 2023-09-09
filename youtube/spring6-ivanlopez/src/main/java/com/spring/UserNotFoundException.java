package com.spring;

public class UserNotFoundException extends RuntimeException {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 3913566491763586464L;

	UserNotFoundException(String message) {
        super(message);
    }
}

