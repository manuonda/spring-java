package com.test.container;

import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepository  extends ListCrudRepository<Customer, Integer>{
    
}
