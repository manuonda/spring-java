package com.eazybytes.springsecuritybassic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsecuritybassic.models.Customer;
import com.eazybytes.springsecuritybassic.repository.CustomerRepository;

@RestController
public class LoginController {

	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String>registerUser(@RequestBody Customer customer) {
		ResponseEntity response = null;
		Customer savedCustomer = null ; 
		try {
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			savedCustomer = customerRepository.save(customer);
			if(savedCustomer.getId() > 0 ) {
				response = ResponseEntity.status(HttpStatus.CREATED).body("Given user details sucessfully registered");
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception ocurred due to " + e.getMessage());
		}
		
		return response;
	}
	
}
