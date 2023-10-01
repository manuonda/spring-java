package com.example.repository;


import com.example.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTest {


    Employee employee;

    @BeforeEach
    public void init(){
        employee = Employee.builder()
                .nombre("david")
                .apellido("garcia")
                .email("manuonda@gmail.com")
                .build();
    }

    @Test
    public void save(){
        
    }
}
