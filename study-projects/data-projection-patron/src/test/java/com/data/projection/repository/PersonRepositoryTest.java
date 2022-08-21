package com.data.projection.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import com.data.projection.entity.Address;
import com.data.projection.entity.Person;
import com.data.projection.projections.PersonLocation;

import io.swagger.models.properties.EmailProperty;

@DataJpaTest
// @Sql("createPersonAddress.sql")
public class PersonRepositoryTest {

	@Autowired 
	 TestEntityManager entityManager;
	
	@Autowired
    PersonRepository repository;
	
	@Test
	void initAll() {
//		for( int i = 0; i < 5 ; i++) {
//	         Person person = new Person();
//	         person.setFirstName("david"+i);
//	         person.setLastName("garcia"+i);
//	         person.setPhoneNumber("number"+i);
//	         person.setId(Long.valueOf(i));
//	         Address address = new Address();
//	         address.setCity("city"+i);
//	         address.setState("state"+i);
//	         address.setPerson(person);
//	         address.setStreet("street"+i);
//	         address.setZipCode("zipCode"+i);
//	         entityManager.persist(person);
//	         //repository.save(person);
//		}
		
	}
	
	@Test
	public void getAll() {
		List<Person> persons = this.repository.findAll();
		System.out.println(persons);
		
	}
	
	@Test
	public void close_projection() {
		PersonLocation personLocation = this.repository.getPersonLocation(1l);
		Optional<PersonLocation> optional = Optional.ofNullable(this.repository.getPersonLocation(1l));
		Assert.isNull(personLocation, "Not null Object with person location 1");
		
	}
	
	
}
