package com.example;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mapstruct.Context;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


// Tutorials
// https://blog.scottlogic.com/2023/02/27/testing-spring-boot-with-testcontainers.html
// https://mkyong.com/spring-boot/spring-boot-testcontainers-example/
// https://jschmitz.dev/posts/testcontainers_how_to_use_them_in_your_spring_boot_integration_tests/
// https://community.ibm.com/community/user/integration/blogs/aritra-das-bairagya/2023/08/14/testcontainers-in-a-java-spring-boot-application
// https://www.adictosaltrabajo.com/2023/05/31/introduccion-testcontainers-en-spring-boot/
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public   class AbstractIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgresql = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("mydatabase")
            .withUsername("myuser")
            .withPassword("secret");


    /*@DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        System.out.println("information  :" +postgresql.getJdbcUrl());
        dynamicPropertyRegistry.add("spring.datasource.url", postgresql::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgresql::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgresql::getPassword);
    }*/

 /*   @BeforeAll
    public static void initUp(){
       postgresql.start();
    }

    @AfterAll
    public static void closeUp(){
        postgresql.stop();
    }*/

}
