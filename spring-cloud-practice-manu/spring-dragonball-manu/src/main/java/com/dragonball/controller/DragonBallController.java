package com.dragonball.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
@RequestMapping("/api/v1/dragonball/characters")
public class DragonBallController {

	
	private static final Logger log = LoggerFactory.getLogger(DragonBallController.class);

	
	private Faker faker = new Faker();
	
	private List<String> characters = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i = 0; i < 20 ; i++) {
			characters.add(faker.dragonBall().character());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<String>> getDragonball(){
		log.info("Get Dragonball characters");
		return ResponseEntity.ok(characters);
	}
}
