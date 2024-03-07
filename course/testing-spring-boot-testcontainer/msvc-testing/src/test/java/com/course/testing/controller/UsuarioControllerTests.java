package com.course.testing.controller;


import com.course.testing.domain.Usuario;
import com.course.testing.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;


//json path
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//get , post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//print
import static  org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// any
import static org.mockito.ArgumentMatchers.any;

// given
import static org.mockito.BDDMockito.given;

// is
import static  org.hamcrest.CoreMatchers.is;






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
         given(this.usuarioService.guardar(any(Usuario.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when - acciones a realizar para testing
        ResultActions response = mockMvc.perform(post("/api/v1/usuarios/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)));

        //then - verificamos la salida
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(usuario.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(usuario.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(usuario.getEmail())));
    }


    //Test Junit obtener todos los usuarios
    @Test
    @DisplayName("Junit Method listar usuarios")
    public void given_when_then() throws Exception {
        //given - preparo nuestros datos
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(Usuario.builder().firstName("david").lastName("garcia").email("david.garcia@gmail.com").build());
        usuarios.add(Usuario.builder().firstName("andres").lastName("garcia").email("andres.garcia@gmail.com").build());
        given(this.usuarioService.getAllUsuarios()).willReturn(usuarios);

        //when - acciones a realizar para testing
        ResultActions resultActions = mockMvc.perform(get("/api/v1/usuarios/listar")
                .contentType(MediaType.APPLICATION_JSON));

        //then - verificamos la salida
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(usuarios.size())));
    }



}
