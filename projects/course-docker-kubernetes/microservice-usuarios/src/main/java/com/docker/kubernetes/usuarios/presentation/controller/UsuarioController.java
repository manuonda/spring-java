package com.docker.kubernetes.usuarios.presentation.controller;


import com.docker.kubernetes.usuarios.business.impl.UsuarioService;
import com.docker.kubernetes.usuarios.domain.dto.UsuarioDTO;
import com.docker.kubernetes.usuarios.domain.entity.Usuario;
import feign.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/usuarios")
@RestController
public class UsuarioController {


    private final UsuarioService usuarioService;


    @Autowired
    private ApplicationContext  context;

    @Autowired
    private Environment environment;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<UsuarioDTO> list = this.usuarioService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/listar2")
    public ResponseEntity<?> listar(){
        Map<String, Object> body =new HashMap<>();
        body.put("users", this.usuarioService.findAll());
        body.put("podinfo", environment.getProperty("MY_POD_NAME") + "-"+ environment.getProperty("MY_POD_IP"));
        body.put("texto", environment.getRequiredProperty("config.texto"));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/crash")
    public void crash(){
        ((ConfigurableApplicationContext) context).close();

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

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        UsuarioDTO usuario = this.usuarioService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PostMapping("/find-usuarios-by-ids")
    public ResponseEntity<List<UsuarioDTO>> findUsuariosByIds(@RequestBody List<Long> ids){
        List<UsuarioDTO> list = this.usuarioService.findByIds(ids);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
