package com.springboot.webflux.apirest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.webflux.apirest.dto.CustomerDTO;
import com.springboot.webflux.apirest.mapper.CustomerMapper;
import com.springboot.webflux.apirest.models.Customer;
import com.springboot.webflux.apirest.repository.CustomerRepository;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


/**
 * Service Customer Repository
 * @author  dgarcia
 * @version 1.0
 * @date    26/7/2023
 *
 */

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerRepository repository;
	    
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	
	private static  CustomerMapper customerMapper;
	
	public CustomerServiceImpl(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	@Override
	public Optional<CustomerDTO> save(CustomerDTO dto) {
		log.info("Method save Customer");
		Customer customer =  customerMapper.toEntity(dto);
	    CustomerDTO newDTO = null;
		try {
			customer = repository.save(customer);
		    newDTO  = customerMapper.toDTO(customer);
	    }catch (Exception e) {
			log.error("Error save customer :",e);
	    	return Optional.ofNullable(null);
		}
		return Optional.of(newDTO);
	}

	@Override
	public Optional<CustomerDTO> findById(Long id) {
		CustomerDTO customerDTO = new CustomerDTO();
		try {
			Optional<Customer> optional = this.repository.findById(id);
			if(optional.isPresent()) {
				customerDTO =customerMapper.toDTO(optional.get());
			}
		}catch(Exception e) {
			log.error("Error find by Id : ",e.getMessage());
			return Optional.ofNullable(null);
		}
		return Optional.of(customerDTO);
		
	}

	@Override
	public List<CustomerDTO> findAll() {
	    log.info("List all customers DTOS");
		List<CustomerDTO> customersDTO = new ArrayList<>();
		List<Customer> customers =  this.repository.findAll();
		customersDTO  =  customers.stream().map((customer) -> customerMapper.toDTO(customer)).collect(Collectors.toList());
		return customersDTO;
	}

	@Override
	public List<CustomerDTO> search(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}
 
	
}
