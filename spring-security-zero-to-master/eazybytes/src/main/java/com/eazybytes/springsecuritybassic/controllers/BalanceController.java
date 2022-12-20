package com.eazybytes.springsecuritybassic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceController {

	@GetMapping("/myBalance")
	public String getBalanceDetails() {
		return "Here are the balance details";
	}
}
