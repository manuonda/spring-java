package com.test.container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class YoutubeTestcontainerApplicationTests {
  
	@Container
	@ServiceConnection
	public static PostgreSQLContainer psql = new PostgreSQLContainer<>("postgres:15.2")
	.withDatabaseName("users")
	.withUsername("postgres")
	.withPassword("postgres");


   @Autowired
   private CustomerRepository repository;


	@Test
	void contextLoads() {

		Assertions.assertTrue(!repository.findAll().iterator().hasNext(),  ()->"where should be no data");
		repository.save(new Customer(null , "David"));		
		Iterable<Customer> customers =  repository.findAll();
		Assertions.assertTrue(!customers.iterator().hasNext(), () -> "where should be some data");
	}

}
