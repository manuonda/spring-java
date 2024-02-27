package com.course.testing.service;


import com.course.testing.domain.Usuario;
import com.course.testing.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    //@Mock
    private UsuarioRepository usuarioRepository;
    //@InjectMocks

    private UsuarioService usuarioService;

    @BeforeEach
    public void setup(){
        System.out.println("setup");
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        usuarioService = new UsuarioServiceImpl(usuarioRepository);

    }

    //Test Junit para guardar Usuario
    @Test
    @DisplayName("Guardar Usuario")
    public void givenUsuarioObjeto_whenGuardamosUsuario_thenReturnUsuarioObjeto()  {
        //given - preparo nuestros datos
         Usuario usuario = Usuario.builder()
                 .firstName("david")
                 .lastName("garcia")
                 .build();
        BDDMockito.given(usuarioRepository.save(usuario)).willReturn(usuario);

        //when - acciones a realizar para testing
        Usuario usuarioSaved = usuarioService.guardar(usuario);

        System.out.println(usuarioSaved.getId());
        //then - verificamos la salida
        Assertions.assertThat(usuarioSaved).isNotNull();
    }

}
