package com.demo.webclient.service;

public interface UserService {
   
	RegisteredUserResponse create(RegisterUserRequest user);
	
	LoginResponse login(LoginRequest loginRequest);
}
