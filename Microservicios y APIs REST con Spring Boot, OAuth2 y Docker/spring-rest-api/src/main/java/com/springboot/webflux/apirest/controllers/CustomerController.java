package com.springboot.webflux.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webflux.apirest.dto.CustomerDTO;
import com.springboot.webflux.apirest.service.ICustomerService;
import org.springframework.http.MediaType;


// best practices https://codeburst.io/spring-boot-rest-microservices-best-practices-2a6e50797115
// https://reflectoring.io/bean-validation-with-spring-boot/

/**
 * 1 - ver el problema de mapper 
 * 2 - agregar documentacion 
 * 3 - agregar test en service impleme 
 * 4 - agregar test en controller 
 * 
 * @author manuonda
 *
 */

@RestController
@RequestMapping(path = {"/api/v1/customers"} ,produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

	
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	
	@Autowired
	private ICustomerService iCustomerService;
	
	@GetMapping("/list")
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<CustomerDTO> customers = this.iCustomerService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}
	
	
	@PostMapping(path = {"/save"})
	public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO parameterDTO) {
		Optional<CustomerDTO> optional = this.iCustomerService.save(parameterDTO);
		if ( optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
		
	}
	
	
}
