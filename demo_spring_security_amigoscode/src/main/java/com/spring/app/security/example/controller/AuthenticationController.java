package com.spring.app.security.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.security.example.UserDetailsServiceImpl;
import com.spring.app.security.example.config.JwtUtils2;
import com.spring.app.security.example.dto.AuthenticationRequest;
import com.spring.app.security.example.dto.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailService;
    private final JwtUtils2 jwtUtils;
    
   
    public ResponseEntity<AuthenticationResponse> authenticate(
    		@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
			
       try {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( authenticationRequest.getUsername(), authenticationRequest.getPassword());
		authenticationManager.authenticate(authentication);
	} catch (BadCredentialsException e) {
		throw new Exception( "Invalid username or password : ", e);
	}	
    	
    	UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
    	String token = jwtUtils.generateToken(userDetails);
    	return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);
    	
    }
}
