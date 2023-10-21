package com.web.metrica.repository;

import com.web.metrica.TestDemoContainer;
import com.web.metrica.domain.Empleado;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.stereotype.Service;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class EmpleadoRepositoryTest {

    Empleado empleado;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            "postgres:latest"
    );

    @BeforeAll
    static void initSetup(){ postgreSQLContainer.start();}

    @AfterAll
    static void stop(){postgreSQLContainer.stop();}

    @BeforeEach
    public void init() {
     this.empleado = Empleado.builder()
             .id(1L)
             .nombre("david")
             .apellido("garcia")
             .build();
    }

    @Test
    @DisplayName("Test Save Empleado ")
    @Order(1)
    public void shouldSaveEmpleado_whenSave_returnObjectEmpelado() {
        //given
        //when
        Empleado empleadoSave = this.empleadoRepository.save(empleado);
        //then
        Assertions.assertThat(empleadoSave).isNotNull();
        Assertions.assertThat(empleadoSave.getId()).isNotNull();
        Assertions.assertThat(empleadoSave.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test Empleado find by Id")
    @Order(2)
    public void shouldFindById_whenFindById_returnObject(){
        // given
        Empleado empleado1 = Empleado.builder()
                .id(2L).nombre("andres").apellido("garcia").build();

        Empleado empleadoSave = this.empleadoRepository.save(empleado1);
        //when
        Optional<Empleado> empleadoFind = this.empleadoRepository.findById(2L);
        Empleado empleado = empleadoFind.get();
        //then
        org.junit.jupiter.api.Assertions.assertEquals(empleado.getId(),2);
        Assertions.assertThat(empleado1.getId()).isEqualTo(2);
        org.junit.jupiter.api.Assertions.assertNotNull(empleado);

    }

}