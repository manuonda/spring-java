package com.course.testing.controller;


import com.course.testing.domain.Usuario;
import com.course.testing.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody @Valid Usuario usuario){
        Usuario usuarioSaved = this.usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSaved);
    }



    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = this.usuarioService.getAllUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }



}
