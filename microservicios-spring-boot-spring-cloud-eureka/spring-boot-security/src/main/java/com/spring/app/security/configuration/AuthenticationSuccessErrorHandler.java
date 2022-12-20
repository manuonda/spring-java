package com.spring.app.security.configuration;

import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;




@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = org.slf4j.LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
       UserDetails user = (UserDetails) authentication.getPrincipal();
	   String message = "Success Login : " + user.getUsername();
	   log.info(message);
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Error en el Login : "+ exception.getMessage();
		log.error(message);
	}

}
