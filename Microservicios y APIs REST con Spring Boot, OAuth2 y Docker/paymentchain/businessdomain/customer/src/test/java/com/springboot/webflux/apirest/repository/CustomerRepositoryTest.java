package com.springboot.webflux.apirest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springboot.webflux.apirest.models.Customer;

/**
 * Class Repository Customer 
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {

	
	private Customer customer;
	
	@Autowired
	private CustomerRepository repository;
	
	
	@BeforeEach
	public void init() {
		customer = Customer.builder()
				.name("David")
				.phone("4243235")
				.build();
	}
	
	
	@DisplayName("Junit test for save customer")
	@Test
	@Order(1)
	public void giveObjectCustomer_whenSave_thenReturnSaveCustomer() {
		// given object customer
		
		//when
		Customer newCustomer = this.repository.save(customer);
		
		//then
		assertThat(newCustomer).isNotNull();
		assertThat(newCustomer.getId()).isGreaterThan(0);
		
	}
	
	@DisplayName("Junit test get customer by username")
	@Test
	public void givenObjectCustomer_whenfindByUsername_thenReturnCustomer() {
		// given object customer
		
		//when
		this.repository.save(this.customer);
		Optional<Customer> optionSearch = this.repository.findByName("David");
		Customer searchCustomer = null;
		if( optionSearch.isPresent()) {
			searchCustomer = optionSearch.get();
		}
		
		//then return 
		assertThat(searchCustomer).isNotNull();
		assertThat(searchCustomer.getName()).isNotEmpty();
		
	}
	
	@DisplayName("Junit Test find All customers")
	@Test
	public void givenListObject_whenListObject_thenReturnListObject() {
		//given
		Customer customer1 = Customer.builder().name("David")
				.phone("132456").build();
		Customer customer2 = Customer.builder().name("Andres")
				.phone("132456").build();
		
		//when
		this.repository.save(customer1);
		this.repository.save(customer2);
		List<Customer> customers = this.repository.findAll();
		
		//then
		assertThat(customers).isNotNull();
		assertThat(customers.size()).isGreaterThan(0);
	}
	
	@DisplayName("Exception when delete id not exist")
	@Test
	public void givenCustomerNotExist_whenDelete_thenThrowException() {
		//given
		Long id = 1234L ;
		//when
		
		//then
		assertThrows(NoSuchElementException.class, () -> this.repository.deleteById(id));
	}
	
	@DisplayName("Test Find By Id Customer")
	@Test
	public void givenObjectCustomer_whenUpdateObject_thenReturnObject() {
		Customer customerSave = this.repository.save(customer);
		//given 
		
		//when
		Optional<Customer> customerSaved =  this.repository.findById(customer.getId());
		if ( customerSaved.isPresent()) {
			customerSave.setName("Andres");
			customerSave.setPhone("123456789");
			this.repository.save(customerSave);
				
		} 
		
		
		//then
		assertThat(customerSave.getName()).isEqualTo("Andres");
		assertThat(customerSave).isNotNull();
		assertThat(customerSave.getId()).isGreaterThan(0);
	}
	
	
	@DisplayName("Test Delete by Id")
	@Test
	public void givenEmployeeObject_whenDeleteObject_thenReturnEmptyObject() {
		
		this.repository.save(customer);
		//given
		
		//when
		this.repository.deleteById(customer.getId());
		Optional<Customer> optional = this.repository.findById(customer.getId());
		
		//then
		assertThat(optional).isEmpty();
	}
	// https://medium.com/@seonggil/how-to-use-builders-efficiently-when-mapping-dto-entity-530bf8d71ed2
	// https://levelup.gitconnected.com/how-to-test-services-in-a-spring-boot-application-a18f86177128
	
	

	
}
