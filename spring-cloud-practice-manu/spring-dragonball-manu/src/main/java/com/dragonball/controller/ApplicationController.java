package com.dragonball.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.dragonball.config.ClientConfigProperties;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {

	
	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private ClientConfigProperties clientConfig;
	
	@GetMapping
	public ResponseEntity<String> getApplicationName(){
		log.info("Get Application Name from configuration server");
		return ResponseEntity.ok(clientConfig.getApplicationName());
	}
	
}
