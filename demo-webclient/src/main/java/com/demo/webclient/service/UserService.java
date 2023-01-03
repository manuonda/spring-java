package com.demo.webclient.service;

import com.demo.webclient.service.dto.LoginRequest;
import com.demo.webclient.service.dto.LoginResponse;
import com.demo.webclient.service.dto.RegisterUserRequest;
import com.demo.webclient.service.dto.RegisteredUserResponse;

public interface UserService {
   
	RegisteredUserResponse create(RegisterUserRequest user);
	
	LoginResponse login(LoginRequest loginRequest);
}
