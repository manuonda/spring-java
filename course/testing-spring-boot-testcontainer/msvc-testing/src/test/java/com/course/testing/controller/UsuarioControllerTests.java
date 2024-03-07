package com.course.testing.controller;


import com.course.testing.domain.Usuario;
import com.course.testing.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTests {


    @Autowired
    private MockMvc mockMvc;

    //injectamos el servicio usuario en la clase UsuarioController
    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;


    //Test Junit para crear usuario
    @Test
    @DisplayName("Test Junit Method para crear usuario")
    public void givenObjectUsuario_whenCreateUsuario_thenReturnObjectoUsuario() throws Exception {
        //given - preparo nuestros datos
        Usuario usuario = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("manuonda@gmail.com")
                .build();

        //Usamos mockito para emular el comportamiento del service de usuario
        BDDMockito.given(this.usuarioService.guardar(ArgumentMatchers.any(Usuario.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when - acciones a realizar para testing
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/usuarios/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)));

        //then - verificamos la salida
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(usuario.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(usuario.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(usuario.getEmail())));
    }


    //Test Junit obtener todos los usuarios
    @Test
    @DisplayName("")
    public void given_when_then() {
        //given - preparo nuestros datos
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(Usuario.builder().firstName("david").lastName("garcia").email("david.garcia@gmail.com").build());
        usuarios.add(Usuario.builder().firstName("andres").lastName("garcia").email("andres.garcia@gmail.com").build());
        BDDMockito.given(this.usuarioService.getAllUsuarios()).willReturn(usuarios);

        //when - acciones a realizar para testing


        //then - verificamos la salida
    }



}
