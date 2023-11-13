package org.event.customer.controller;

import org.event.customer.dto.CustomerDTO;
import org.event.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/create")
    public void save(@RequestBody CustomerDTO customer) {
     customerService.registerCustomer(customer);
    }
}
