package com.data.projection.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.data.projection.entity.Address;
import com.data.projection.entity.Person;
import com.data.projection.repository.AddressRepository;
import com.data.projection.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PopularTest implements InitializingBean {
	
	// InitializeBean => replace @PostConstruct()
	
	private Logger logger  = LoggerFactory.getLogger(PopularTest.class);
	
	
	private AddressRepository addressRepository;
	private PersonRepository personRepository;
   
	
	public PopularTest(AddressRepository addressRepository, PersonRepository personRepository) {
		super();
		this.addressRepository = addressRepository;
		this.personRepository = personRepository;
	}

	public void afterPropertiesSet() throws Exception {
		log.info("=== Go to populate the database");
		populate();
		log.info("== fin");
	}
	
	public void populate() {
		for( int i = 0; i < 5 ; i++) {
	         Person person = new Person();
	         person.setFirstName("david"+i);
	         person.setLastName("garcia"+i);
	         person.setId(Long.valueOf(i));
	         Address address = new Address();
	         address.setCity("city"+i);
	         address.setState("state"+i);
	         address.setPerson(person);
	         address.setStreet("street"+i);
	         address.setZipCode("zipCode"+i);
	         this.personRepository.save(person);
		}
		
	}

	
}
