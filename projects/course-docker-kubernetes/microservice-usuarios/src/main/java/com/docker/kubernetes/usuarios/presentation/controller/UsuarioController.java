package com.docker.kubernetes.usuarios.presentation.controller;


import com.docker.kubernetes.usuarios.business.impl.UsuarioService;
import com.docker.kubernetes.usuarios.domain.dto.UsuarioDTO;
import com.docker.kubernetes.usuarios.domain.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RequestMapping("/api/v1/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return this.usuarioService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<UsuarioDTO> saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioDTO> update(@RequestBody @Valid UsuarioDTO usuarioDTO,
                                             @PathVariable("id") Long id){

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){

    }
}
