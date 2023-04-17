package com.dev4j.client.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev4j.client.config.DragonBallConfiguration;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/application-name")
public class ApplicationNameController {
   
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationNameController.class);

	
	@Autowired
	private DragonBallConfiguration configuration;
	
	
	/**
	 * Relacionado con la metrica
	 */
	@Autowired
	private MeterRegistry registry;
	
	
	
	@GetMapping
	@Timed("dev4js.dragonball.name.get")
	public  ResponseEntity<String> getAppName(){
		log.info("Get Application Name");
		int value =  new Random().nextInt(5);
		//url para probar : http://localhost:8082/actuator/metrics/devs4j.dragonball.name
		registry.counter("devs4j.dragonball.name", "level",( value < 3) ?  "jr": "sr").increment(value);
		return ResponseEntity.ok(configuration.getAppName());
	}
}
