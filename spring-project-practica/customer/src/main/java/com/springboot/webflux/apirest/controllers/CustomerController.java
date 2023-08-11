package com.springboot.webflux.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webflux.apirest.dto.CustomerDTO;
import com.springboot.webflux.apirest.exception.BusinessException;
import com.springboot.webflux.apirest.exception.RequestException;
import com.springboot.webflux.apirest.service.ICustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;

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

	@Operation(operationId = "List customers", description = "Muestra el listado de customers")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "List all customers",
			content = {@Content(mediaType ="application/json")})
	})
	@GetMapping("/list")
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<CustomerDTO> customers = this.iCustomerService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}
	
	
	
	@Operation(operationId = "Save Customer", description = "Permite guardar el registro de customer")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
				description = "Save Customer correct",
				content= {@Content(mediaType = "application/json")}
				),
	    @ApiResponse(responseCode = "500",
	         description="Error Save Customer"
	    ),
		        
		        
	})
	@PostMapping(path = {"/save"})
	public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO parameterDTO) {
		
		
		if (  parameterDTO.getNameCustomer().isEmpty()) {
			throw new RequestException("P-500","Name Customer is Required" );
		}
		
		if ( parameterDTO.getNameCustomer().equals("manuonda")) {
			throw  new BusinessException("P-300", HttpStatus.INTERNAL_SERVER_ERROR, "Name Customer Really exists");
		}
		
		Optional<CustomerDTO> optional = this.iCustomerService.save(parameterDTO);
		if ( optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
		
	}
	
	
	@Operation( operationId = "Update Customer", description ="Permite update el registro de Customer")
	@ApiResponses({
		@ApiResponse(responseCode = "200" ,  description = "Update Customer OK",
				  content = {@Content(mediaType = "application/json")}),
		@ApiResponse(responseCode="500" , description="Error Update Customer",
			 	  content = {@Content(mediaType = "application/json")})			
	
	})	
	@PutMapping(path = {"/update/{id}"})
	public ResponseEntity<CustomerDTO> update(@PathParam("id") Long id, @RequestBody CustomerDTO parameter) {
		
		Optional<CustomerDTO> optional = this.iCustomerService.update(id, parameter);
		if ( optional.isEmpty()){
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
		
	}
	
	
	@Operation(operationId = "Delete Customer", description="Permite borrar un registro de Customer")
	@ApiResponses({
		@ApiResponse(
			responseCode = "200" , description = "Delete customer",
			content = {@Content(mediaType = "application/json")}),
	   @ApiResponse(
			   responseCode = "500" , description ="Customer Not Found")
	})
	@DeleteMapping(path= {"/delete"})
	public ResponseEntity<String>  delete(@PathVariable("id") Long id) {
		this.iCustomerService.deleteCustomer(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@Operation(operationId = "Get By ID" , description = "Permite obtener un customer by id")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description="Cuando se obtiene el customer correctamente",
		content = {@Content(mediaType ="application/json")})
	})
	public ResponseEntity<CustomerDTO> getById(@PathVariable("id") Long id ){
	  Optional<CustomerDTO> optional = 	this.iCustomerService.findById(id);
	    if ( optional.isEmpty()) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	
	
	
	
	
	
	
}
