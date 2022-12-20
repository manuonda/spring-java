package com.spring.app.security.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingController {

	@GetMapping
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Hello from our Api");
	}
	
	@GetMapping("/say-good-bye")
	public ResponseEntity<String> sayGoodBye(){
		return ResponseEntity.ok("Good by and see you later");
	}
}
