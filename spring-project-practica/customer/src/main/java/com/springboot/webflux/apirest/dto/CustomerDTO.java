package com.springboot.webflux.apirest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.webflux.apirest.models.Customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Class Customer DTO 
 * @author dgarcia
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

	
	@Name("id")
	private Long id;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotNull
	@Name("Name Customer")
	private String nameCustomer;
	
	private String phone;
	
	
	
}
