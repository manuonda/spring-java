package org.event.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.event.customer.domain.Customer;
import org.event.customer.repository.CustomerRepository;
import org.event.customer.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CustomerService {

    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(CustomerDTO customerDTO) {
        Customer customer =  Customer.builder()
                .firstName(customerDTO.firstName())
                .lastName(customerDTO.lastName())
                .gender(customerDTO.gender())
                .email(customerDTO.email())
                .phoneNumber(customerDTO.phoneNumber())
                .idType(customerDTO.idType())
                .idValue(customerDTO.idValue())
                .created(LocalDateTime.now())
                .build();
        customerRepository.save(customer);
        logger.info("Customer save data");
    }
}
