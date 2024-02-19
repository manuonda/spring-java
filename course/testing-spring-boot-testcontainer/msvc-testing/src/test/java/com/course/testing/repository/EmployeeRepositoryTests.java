package com.course.testing.repository;


import com.course.testing.domain.entity.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.type.ClassStack;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    /**Injectamos la dependencia de EmployeeRepository */
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;
    @BeforeEach
    public void setUp(){

    }

   @Test
   @DisplayName("Test Save Employee Object")
   public void givenEmployee_whenSaveEmployee_thenReturnEmployeeObject(){
      //given
      Employee employee = Employee.builder()
              .firstName("david")
              .lastName("garcia")
              .email("manuonda@gmail.com")
              .build();
      //when
     Employee employeeSaved =  this.employeeRepository.save(employee);

      //then
      assertThat(employeeSaved).isNotNull();
      assertThat(employeeSaved.getId()).isGreaterThan(0);
      assertThat(employeeSaved.getFirstName()).contains("david");

   }


   // JUnit Test for get all employees operation
   @Test
   @DisplayName("Test for List Employee")
   public void givenEmployees_whenGetListEmployee_thenReturnListEmployee(){

       //given
       Employee employee = Employee.builder()
               .firstName("David")
               .lastName("Garcia")
               .email("david.garcia@gmail.com")
               .build();
       Employee employee1 = Employee.builder()
               .firstName("jhon")
               .lastName("fox")
               .email("jhon.fox@gmail.com")
               .build();

       employeeRepository.save(employee);
       employeeRepository.save(employee1);
       //when
       List<Employee> list = this.employeeRepository.findAll();

       //then
       assertThat(list.size()).isGreaterThan(0);
       assertThat(list).isNotNull();
       assertThat(list.size()).isEqualTo(2);
   }


    //Junit test get by id Employee
    @Test
    @DisplayName("Test Get By Id Employee")
    public void givenEmployeeObject_whenFindByIdEmployee_thenReturnEmployeeObject() {
       //given - preparo nuestros datos
        Employee employee = Employee.builder()
                .firstName("david")
                .lastName("Alexander")
                .email("david.alexander@gmail.com")
                .build();
        this.employeeRepository.save(employee);

        //when - acciones a realizar para testing
        Employee employeeFind = this.employeeRepository.findById(employee.getId()).get();

        //then - verificamos la salida
        assertThat(employeeFind).isNotNull();

    }

    //Test for verificar if exist el employee by el  email ingresado
    @Test
    @DisplayName("Test Find Employee  by el campo email")
    public void givenEmployeeObject_whenFindByEmail_thenReturnObjectEmployee() {
        //given - preparo nuestros datos
        Employee employee = Employee.builder()
                .firstName("david")
                .lastName("Alexander")
                .email("david.alexander@gmail.com")
                .build();
        this.employeeRepository.save(employee);
        //when
        Employee employeeFind = this.employeeRepository.findByEmail("david.alexander@gmail.com").get();

        //then - verificamos la salida
        assertThat(employeeFind).isNotNull();
    }


    //Test for update employee operation
    @Test
    @DisplayName("Test update Employee")
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdateEmployee() {
        //given - preparo nuestros datos
        Employee employee = Employee.builder()
                .firstName("david")
                .lastName("onda")
                .email("manuonda@gmail.com")
                .build();
        this.employeeRepository.save(employee);
        //when - acciones a realizar para testing
        Employee employeeFind = this.employeeRepository.findById(employee.getId()).get();

        employeeFind.setFirstName("andres");
        employeeFind.setLastName("carter");
        Employee updateEmployee = this.employeeRepository.save(employeeFind);

        //then - verificamos la salida
        assert(updateEmployee.getEmail()).equals("manuonda@gmail.com");
        assert(updateEmployee.getFirstName()).equals("andres");

    }

    //Test for delete employee
    @Test
    @DisplayName("Test for delete employee operation")
    public void givenEmployeeObject_whenDeleteObject_thenRemoveEmployee() {
        //given - preparo nuestros datos
        Employee employee = Employee.builder()
                .firstName("david")
                .lastName("onda")
                .email("manuonda@gmail.com")
                .build();
        this.employeeRepository.save(employee);

        //when - acciones a realizar para testing
         this.employeeRepository.delete(employee);
         //this.employeeRepository.deleteById(employee.getId());
         Optional<Employee> employeeOptional = this.employeeRepository.findById(employee.getId());

        //then - verificamos la salida
        assertThat(employeeOptional).isEmpty();
    }

    //Test index with firstName and LastName
    @Test
    @DisplayName("Test JPQL  for query using JPQL with index")
    public void givenFirstNameLastName_whenFindByJPQLIndex_thenReturnEmployeeObject() {
        //given - preparo nuestros datos
        Employee employee = Employee.builder()
                .firstName("david")
                .lastName("onda")
                .email("manuonda@gmail.com")
                .build();
        this.employeeRepository.save(employee);
        String firstName = "david";
        String lastName = "onda";
        //when - acciones a realizar para testing
        Employee employeeFind = this.employeeRepository.findByJPQLIndex(firstName, lastName).get();

        //then - verificamos la salida
        assertThat(employeeFind).isNotNull();
    }


    //Test para custom sql native query indexacion
    @Test
    @DisplayName("Test custom query usando JPQL pero con parametros")
    public void givenFirstNameLastName_whenFindByJPQLParam_thenReturnEmployeeObject() {
        //given - preparo nuestros datos
        Employee employee = Employee.builder()
                .firstName("david")
                .lastName("onda")
                .email("manuonda@gmail.com")
                .build();
        this.employeeRepository.save(employee);
        String firstName = "david";
        String lastName = "onda";
        //when - acciones a realizar para testing
        Employee employeeFind = this.employeeRepository.findByJPQLParam(firstName, lastName).get();

        //then - verificamos la salida
        assertThat(employeeFind).isNotNull();
    }

      //Test Function Sql Native indexacion
      @Test
      @DisplayName("")
      public void givenFirstNameLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {
          //given - preparo nuestros datos
          Employee employee = Employee.builder()
                  .firstName("david")
                  .lastName("onda")
                  .email("manuonda@gmail.com")
                  .build();
          this.employeeRepository.save(employee);
          String firstName = "david";
          String lastName = "onda";

          //when - acciones a realizar para testing
          Employee employeeFind = employeeRepository.findBySqlNativeIndex(firstName, lastName).get();

          //then - verificamos la salida
          assertThat(employeeFind).isNotNull();

      }

    //Test Function Sql Native con Parametros
    @Test
    @DisplayName("")
    public void givenFirstNameLastName_whenFindByNativeSQLParametros_thenReturnEmployeeObject() {
        //given - preparo nuestros datos
        Employee employee = Employee.builder()
                .firstName("david")
                .lastName("onda")
                .email("manuonda@gmail.com")
                .build();
        this.employeeRepository.save(employee);
        //when - acciones a realizar para testing
        Employee employeeFind = employeeRepository.findBySqlNativeParametros(employee.getFirstName(),
                employee.getLastName()).get();
        //then - verificamos la salida
        assertThat(employeeFind).isNotNull();

    }



}
