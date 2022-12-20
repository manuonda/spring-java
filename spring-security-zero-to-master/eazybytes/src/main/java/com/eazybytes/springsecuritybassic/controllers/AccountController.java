package com.eazybytes.springsecuritybassic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	@GetMapping("/myAccount")
	public String getAccountDetails() {
		return "getMyAccount";
	}
}
