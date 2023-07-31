package com.springboot.webflux.apirest.service;

import java.util.List;
import java.util.Optional;

import com.springboot.webflux.apirest.dto.CustomerDTO;

public interface ICustomerService {

	
	Optional<CustomerDTO> save(CustomerDTO dto);
	
	Optional<CustomerDTO> findById(Long id);
	
	List<CustomerDTO> findAll();
	
	
	List<CustomerDTO> search(CustomerDTO customerDTO);
}
