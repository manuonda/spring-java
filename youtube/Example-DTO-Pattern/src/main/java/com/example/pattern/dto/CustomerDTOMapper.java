package com.example.pattern.dto;

import com.example.pattern.dto.Customer;
import com.example.pattern.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class  CustomerDTOMapper implements Function<Customer, CustomerDTO> {

    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAge()
        );
    }
}