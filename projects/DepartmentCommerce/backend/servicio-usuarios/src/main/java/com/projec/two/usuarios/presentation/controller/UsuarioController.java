package com.projec.two.usuarios.presentation.controller;


import com.projec.two.usuarios.business.service.UsuarioService;
import com.projec.two.usuarios.domain.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<UsuarioDTO>> getList() {
       return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.list());
    }



    @ApiResponse(responseCode = "200" , description = "Save Usuario" )
    @PostMapping("/save")
    public ResponseEntity<?> saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) throws Exception {
        UsuarioDTO saveUsuarioDTO =  this.usuarioService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(saveUsuarioDTO);
    }



}
