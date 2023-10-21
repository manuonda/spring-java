package com.example.repository;

import com.example.AbstractIntegrationTest;
import com.example.domain.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class EmployeeRepositoryTest   {


    @Autowired
    private EmployeeRepository employeeRepository;


    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );

    @BeforeAll
     static void initSetup(){
      postgres.start();
    }
    @AfterAll
     static void afterAll(){
        //postgres.stop();
    }
    Employee employee;

    @BeforeEach
    void init() {
      employee = Employee.builder()
              .nombre("David Garcia")
              .apellido("Garcia")
              .email("manuonda@gmail.com")
              .build();
    }

    @Test
    @DisplayName("Save Employee")
    public void whenSaveEmploye_returnObjectEmploy(){
        // given

        //when
        Employee employee1 = this.employeeRepository.save(employee);
        Employee employee2 = this.employeeRepository.save(employee);
        System.out.println("Employee1: " + employee1);
        System.out.println("Employee2 : " + employee2);

        //then
        Assertions.assertThat(employee1).isNotNull();
        Assertions.assertThat(employee1.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("List all")
    public void whenListAll_returnListObject(){
        // given
        Employee employee1 = Employee.builder()
                .nombre("Andres")
                .apellido("Garcia")
                .email("andres@gmail.com")
                .build();
        Employee employee2 = Employee.builder()
                .nombre("David")
                .apellido("Garcia")
                .email("david@gmail.com")
                .build();
        this.employeeRepository.saveAll(List.of(employee1, employee2));

        //when
        List<Employee> employeeList = this.employeeRepository.findAll();

        //then
        Assertions.assertThat(employeeList).isNotEmpty();
        Assertions.assertThat(employeeList.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find  by Email")
    void givenEmployeeEmail_whenFindByIdEmail_returnObjectEmployee(){
        //given
        Employee employee1 = Employee.builder()
                .nombre("David")
                .apellido("Garcia")
                .email("manuonda@gmail.com")
                .build();
        this.employeeRepository.save(employee1);

        //when
        Optional<Employee> optionalEmployee =  this.employeeRepository.findByEmail("manuonda@gmail.com");
        Employee employee2 = null;
        if ( optionalEmployee.isPresent()) {
            employee2 = optionalEmployee.get();
        }

        //then
        Assertions.assertThat(employee2.getEmail()).isEqualTo("manuonda@gmail.com");
        Assertions.assertThat(employee2.getEmail()).isEqualTo("manuonda32@gmail.com");

    }

}