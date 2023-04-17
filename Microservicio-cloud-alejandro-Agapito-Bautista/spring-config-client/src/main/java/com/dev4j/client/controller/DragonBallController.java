package com.dev4j.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev4j.client.service.FooService;
import com.github.javafaker.Faker;

@RestController
@RequestMapping("/api/v1/dragonball/characters")
public class DragonBallController {

	
	
	private static final Logger log = LoggerFactory.getLogger(DragonBallController.class);

	private Faker faker = new Faker();
	
	private List<String> characters = new ArrayList<>();
	
	@Autowired
	FooService foo;
	

	
	
	
	@PostConstruct
	public void init() {
		for(int i =0 ; i < 20 ; i++) {
		   characters.add(faker.dragonBall().character());  
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<String>> getCharacters(){
		log.info("Getting characters db");
		foo.printLog();
		return ResponseEntity.ok().body(characters);
	}
}
