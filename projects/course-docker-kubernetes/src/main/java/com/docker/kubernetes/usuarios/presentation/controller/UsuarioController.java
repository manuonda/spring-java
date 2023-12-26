package com.docker.kubernetes.usuarios.presentation.controller;


import com.docker.kubernetes.usuarios.business.impl.UsuarioService;
import com.docker.kubernetes.usuarios.domain.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<UsuarioDTO> findAll(){
        return this.usuarioService.findAll();
    }
}
