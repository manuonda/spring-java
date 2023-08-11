package com.springboot.webflux.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.webflux.apirest.models.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByName(String name);
	
	
}
