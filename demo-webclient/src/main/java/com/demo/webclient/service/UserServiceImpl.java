package com.demo.webclient.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.webclient.context.AbstractClient;
import com.demo.webclient.service.dto.LoginRequest;
import com.demo.webclient.service.dto.LoginResponse;
import com.demo.webclient.service.dto.RegisterUserRequest;
import com.demo.webclient.service.dto.RegisteredUserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl extends AbstractClient implements UserService{
	
	
	protected UserServiceImpl(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public RegisteredUserResponse create(RegisterUserRequest user) {
		String uri = baseUrl + "/user";
		log.info("Going to create {}", user);
		ResponseEntity<RegisteredUserResponse> response = restTemplate.postForEntity(uri, user, RegisteredUserResponse.class);
		if ( response.getStatusCode().is2xxSuccessful()) {
			log.info("Sucessfully user creation : {}", response.getBody().getStatus());
			return response.getBody();
		}
		
		log.error("Error in user creation.- httpStatus was : {} ", response.getStatusCode());
		throw new RuntimeException("Error")
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
