package com.eazybytes.springsecuritybassic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardsController {

	@GetMapping("/myCards")
	public String getCardDetails() {
		return "My Cards";
	}
	
}

