package com.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.models.dto.TokenDTO;
import com.authentication.models.dto.UserDTO;
import com.authentication.service.AuthService;

@RestController
@RequestMapping("/auth/")
public class AuthController {

	@Autowired 
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@RequestBody UserDTO user) {
		return ResponseEntity.ok(authService.login(user));
	}
	
	
	@PostMapping("/validate")
	public ResponseEntity<TokenDTO> validate(@RequestParam String token) {
		return ResponseEntity.ok(authService.validate(token));
	}
	

	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
		return ResponseEntity.ok(authService.save(user));
	}
	
}
