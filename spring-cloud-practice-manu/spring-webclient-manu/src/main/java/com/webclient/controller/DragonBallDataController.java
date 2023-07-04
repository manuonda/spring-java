package com.webclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webclient.service.ResponseDragonBallService;

@RestController
@RequestMapping("/api/v1/webclient-dragonball/characters")
public class DragonBallDataController {

	
	@Autowired
	private ResponseDragonBallService dragonBallService;
	
	@GetMapping
	public ResponseEntity<List<String>> getDataDragonBall(){
		return ResponseEntity.ok().body(dragonBallService.getDragonBallData());
	}
	
	
}
