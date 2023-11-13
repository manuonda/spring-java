package com.customer.service;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    Customer findById(Long id);
    Customer save(CustomerDTO customerDTO);

    List<CustomerDTO> findAll();
    CustomerDTO update(CustomerDTO customerDTO, Long id);

    void delete(Long id);

    CustomerDTO findByEmail(String email);
}
