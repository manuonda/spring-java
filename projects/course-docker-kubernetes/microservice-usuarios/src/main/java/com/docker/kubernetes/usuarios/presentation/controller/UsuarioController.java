package com.docker.kubernetes.usuarios.presentation.controller;


import com.docker.kubernetes.usuarios.business.impl.UsuarioService;
import com.docker.kubernetes.usuarios.domain.dto.UsuarioDTO;
import com.docker.kubernetes.usuarios.domain.entity.Usuario;
import feign.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RequestMapping("/api/v1/usuarios")
@RestController
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<UsuarioDTO> list = this.usuarioService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/save")
    public ResponseEntity<UsuarioDTO> saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        UsuarioDTO usuario = this.usuarioService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioDTO> update(@RequestBody @Valid UsuarioDTO usuarioDTO,
                                             @PathVariable("id") Long id){
     UsuarioDTO usuario = this.usuarioService.update(usuarioDTO, id);
     return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
      this.usuarioService.delete(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
