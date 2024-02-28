package com.course.testing.service;


import com.course.testing.domain.Usuario;
import com.course.testing.exception.ResourceNotFoundException;
import com.course.testing.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    @Mock //objeto simulado de la clase repository
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    // private UsuarioService usuarioService;


    private Usuario usuario;

    @BeforeEach
    public void setup(){
        System.out.println("setup");
       // usuarioRepository = Mockito.mock(UsuarioRepository.class);
        // usuarioService = new UsuarioServiceImpl(usuarioRepository);

        usuario = Usuario.builder()
                .id(1L)
                .firstName("david")
                .lastName("garcia")
                .build();

    }

    //Test Junit para guardar Usuario
    @Test
    @DisplayName("Guardar Usuario")
    public void givenUsuarioObjeto_whenGuardamosUsuario_thenReturnUsuarioObjeto()  {
        //given - preparo nuestros datos

        BDDMockito.given(usuarioRepository.save(usuario)).willReturn(usuario);

        //when - acciones a realizar para testing
        Usuario usuarioSaved = usuarioService.guardar(usuario);

        //then - verificamos la salida
        Assertions.assertThat(usuarioSaved).isNotNull();
    }


    //Test Junit para guardar Usuario
    @Test
    @DisplayName("Exception al guardar el usuario , existe el email ")
    public void givenExistingEmail_whenGuardamosUsuario_thenThrowsException()  {
        //given - preparo nuestros datos
        BDDMockito.given(usuarioRepository.findByEmail(usuario.getEmail()))
                        .willReturn(Optional.of(usuario));

        // No llega a ejecutarse  BDDMockito.given(usuarioRepository.save(usuario)).willReturn(usuario);
        //when - acciones a realizar para testing
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class,() -> {
           usuarioService.guardar(usuario);
        });

        //then  : nunca se ejecuta el llamado al repositorio por la exception
        verify(this.usuarioRepository, never()).save(any(Usuario.class));
    }


    //Test Junit para obtener todos los usuarios
    @Test
    @DisplayName("Junit Test para obtener todos los usuarios")
    public void givenUsuariosList_whenFindAllUsuarios_thenReturnListUsuarios() {
        //given - preparo nuestros datos
        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .firstName("andres")
                .lastName("garcia")
                .build();

        BDDMockito.given(this.usuarioRepository.findAll()).willReturn(List.of(usuario, usuario2));
        //when - acciones a realizar para testing
        List<Usuario> listUsuarios = this.usuarioService.getAllUsuarios();

        //then - verificamos la salida
        Assertions.assertThat(listUsuarios).isNotNull();
        Assertions.assertThat(listUsuarios.size()).isEqualTo(2);

        verify(this.usuarioRepository).findAll();

    }

    //Test Junit retorna listado vacio de usuarios
    @Test
    @DisplayName("Junit test obtiene listado vacio de usuarios")
    public void givenVacioListadoUsuario_whenFindAllUsuario_thenReturnListadoVacioUsuarios() {

        //given - preparo nuestros datos
        BDDMockito.given(this.usuarioRepository.findAll()).willReturn(Collections.emptyList());

        //when - acciones a realizar para testing
        List<Usuario> listUsuarios = this.usuarioService.getAllUsuarios();

        //then - verificamos la salida
        Assertions.assertThat(listUsuarios).isEmpty();
        Assertions.assertThat(listUsuarios.size()).isEqualTo(0);

        verify(this.usuarioRepository).findAll();
    }


}
