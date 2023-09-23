package com.example.pattern.dto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private  final CustomerDTOMapper customerDTOMapper;

    public CustomerController(
            CustomerDTOMapper customerDTOMapper) {
        this.customerDTOMapper = customerDTOMapper;
    }

    @GetMapping("/")
    public List<CustomerDTO> get() {
        //Todo : all this in layer service implement
        List<Customer> customers =List.of(
                 new Customer(1, "david ", "manuonda", 23),
                 new Customer(2, "andres","garcia",24));
        return customers.stream().map(customerDTOMapper)
                .collect(Collectors.toList());
    }
}
