package com.projec.two.usuarios;

import lombok.Data;
import org.mapstruct.Context;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@DataJpaTest
@Testcontainers
//@ActiveProfiles("medium")
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class BaseRepositoryTest {


    @ServiceConnection
    public static PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
    }

    static {
        postgresContainer().start();
    }
}
