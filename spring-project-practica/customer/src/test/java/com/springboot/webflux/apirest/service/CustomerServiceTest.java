package com.springboot.webflux.apirest.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.springboot.webflux.apirest.dto.CustomerDTO;
import com.springboot.webflux.apirest.mapper.CustomerMapper;
import com.springboot.webflux.apirest.models.Customer;
import com.springboot.webflux.apirest.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
   
   @InjectMocks
   private CustomerServiceImpl customerService;
   
   @Mock
   private CustomerRepository customerRepository;
   
   @Spy
   private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
   
   private Customer customer;
   
   
   @Before(value = "")
   public void init() {
	 //MockitoAnnotations.initMocks(this);   
	   customerService = new CustomerServiceImpl(customerRepository, customerMapper);
   }
   
   @BeforeEach
   public void initEach() {
	   this.customer = Customer.builder()
			   .name("David")
			   .phone("112233")
			   .build();
   }
   
   
   @Order(1)
   @DisplayName("Get Lista all customers")
   @Test
   public void givenCustomerList_whenGetAllCustomers_thenReturnListAllCustomers() {
	   // given
	   Customer customer1 = Customer.builder()
			   .name("David")
			   .phone("111")
			   .build();
	   
	   Customer customer2 = Customer.builder()
			   .name("Andres")
			   .phone("222")
			   .build();
	  
	   given(customerRepository.findAll()).willReturn(List.of(customer, customer1,customer2));
	   
	   //when 
	   List<CustomerDTO> customers = this.customerService.findAll();
	   
	   //then 
	   assertThat(customers).isNotNull();
	   assertThat(customers.size()).isGreaterThan(0);
	   verify(this.customerRepository).findAll();
   }
   
   @Order(2)
   @DisplayName("Get List customers empty")
   @Test
   public void givenEmptyEmployeeList_whenEmptyAllCustomers_returnEmptyeCustomerList() {
	   //given 
	   given(this.customerRepository.findAll()).willReturn(Collections.emptyList());
	   
	    //when
	   List<CustomerDTO> customers = this.customerService.findAll();
	   
	   //then
       assertThat(customers).isEmpty();
       assertThat(customers.size()).isEqualTo(0);
       verify(customerRepository).findAll();
   }
   
   @Order(3)
   @DisplayName("Get Customer By Id")
   @Test
   public void givenCustomerObject_whenCustomerObject_returnCustomerObject() {
	   //given
	   given(this.customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));
	   
	   // when 
	  CustomerDTO newCustomer = this.customerService.findById(customer.getId()).get();
	  
	  //then 
	  assertThat(newCustomer).isNotNull();
	  verify(this.customerRepository).findById(customer.getId());
   }
   
   @Order(4)
   @DisplayName("Save Customer")
   @Test
   public void givenCustomerObject_whenSaveCustomer_returnCustomerObject() {
	   //given 
	 //  given(this.customerRepository.save(customer)).willReturn(customer);
	   CustomerDTO customerDTO = this.customerMapper.toDTO(customer);
	  // customerDTO.setId(null);
	   //when
	   CustomerDTO saveCustomer = this.customerService.save(customerDTO).get();
	   
	   //then
	   
	   assertThat(saveCustomer).isNotNull();
	   assertThat(customerDTO.getId()).isGreaterThan(0);
   }
   
   @DisplayName("Not exist customer")
   @Test
   public void givenEmptyObject_whenGetObjectCustomer_returnEmptyObject() {
	   //given 
	   
	   //when 
	   when(this.customerRepository.findById(1L)).thenReturn(Optional.empty());
	   
	   //then
	   Assertions.assertThrows(EntityNotFoundException.class, () -> customerService.findById(1L));
	   verify(customerRepository , times(1)).findById(1L);
       verifyNoMoreInteractions(customerRepository);
   
  }
   
   @DisplayName("Delete customer")
   @Test
   public void givenDeleteCustomer_whenExistCustomer_returnEmpty() {
	   
   }
}