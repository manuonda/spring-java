package com.eazybytes.springsecuritybassic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoansController {

	@GetMapping("/")
	public String getLoans() {
		return "Get Loans";
	}
}
