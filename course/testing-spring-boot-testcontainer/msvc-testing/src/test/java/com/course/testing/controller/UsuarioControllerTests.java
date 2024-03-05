package com.course.testing.controller;


import com.course.testing.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;



}
