package com.project.two.producto;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.stereotype.Service;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
public class TestConfiguration {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgresql = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("mydatabase")
            .withUsername("myuser")
            .withPassword("secret");

}
