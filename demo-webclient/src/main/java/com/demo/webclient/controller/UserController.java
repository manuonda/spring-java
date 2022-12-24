package com.demo.webclient.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("worldcup/users")

public class UserController {

	private final UserService userService;
	
	@PostMapping
	public RegisteredUserResponse registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
		return this.userService.create(registerUserRequest);
	}
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody  LoginRequest loginRequest) {
		return this.userService.login(loginRequest);
	}
	
}
