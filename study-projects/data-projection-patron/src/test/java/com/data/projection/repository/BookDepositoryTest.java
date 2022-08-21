package com.data.projection.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
public class BookDepositoryTest {

	@Autowired 
	private TestEntityManager em;
	
	@Autowired
	private BookRepository repository;
	
	
	@Test() 
	public void load() {
		
	}
	
	
	
}
