package com.projec.two.usuarios.repository;

import com.projec.two.usuarios.BaseRepositoryTest;
import com.projec.two.usuarios.domain.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class UsuarioRepositoryTest {



    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Save Usuario ")
    public void shouldSaveUsuario_whenCallSave() {
        //given
        Usuario usuario =  Usuario.builder()
                .userName("dgarcia")
                .lastName("garcia")
                .password("123456")
                .repeatPassword("123456")
                .email("manuonda@gmail.com")
                .build();
        //when
        Usuario usuarioSave = usuarioRepository.save(usuario);

        //then
        Assertions.assertThat(usuarioSave.getId()).isGreaterThan(0);
        Assertions.assertThat(usuarioSave).isNotNull();
        Assertions.assertThat(usuarioSave.getUserName()).isEqualTo("dgarcia");
    }

    @Test
    @DisplayName("Update usuario")
    public void shouldUpdateUsuario_whenCallUpdate() {
        //given
        Usuario usuario =  Usuario.builder()
                .userName("manuonda")
                .lastName("garcia")
                .password("123456")
                .repeatPassword("123456")
                .build();
        //when
        Usuario usuarioSave  = usuarioRepository.save(usuario);

        //then
    }

    @Test
    public void name() {
        System.out.println("prueba");
    }
}

