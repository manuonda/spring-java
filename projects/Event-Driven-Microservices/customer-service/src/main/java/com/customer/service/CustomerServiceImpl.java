package com.customer.service;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;
import com.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements   CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return null;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CustomerDTO findByEmail(String email) {
        return null;
    }
}
