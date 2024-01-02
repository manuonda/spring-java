package com.docker.kubernetes.curso.presentation.controller;


import com.docker.kubernetes.curso.business.service.CursoService;
import com.docker.kubernetes.curso.business.service.CursoUsuarioService;
import com.docker.kubernetes.curso.domain.dto.CursoDTO;
import com.docker.kubernetes.curso.domain.dto.CursoUsuarioDTO;
import com.docker.kubernetes.curso.domain.dto.UsuarioDTO;
import feign.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {


    private final CursoService cursoService;
    private final CursoUsuarioService cursoUsuarioService;

    public CursoController(CursoService cursoService, CursoUsuarioService cursoUsuarioService) {
        this.cursoService = cursoService;
        this.cursoUsuarioService = cursoUsuarioService;
    }


    @GetMapping("")
    public ResponseEntity<?> findAll(){
      List<CursoDTO> list = this.cursoService.findAll();
      return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid CursoDTO dto){
        CursoDTO save = this.cursoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid CursoDTO dto, @PathVariable("id") Long id){
        CursoDTO update = this.cursoService.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        this.cursoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.findById(id));
    }

    @PostMapping("/asignar-curso")
    public ResponseEntity<?> asignarCursoToUsuario(@RequestBody CursoUsuarioDTO dto){
        CursoUsuarioDTO cursoUsuarioDTO =  this.cursoUsuarioService.asignarUsuarioToCurso(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoUsuarioDTO);
    }


    /**
     * Funcion que retorna los usuarios
     * por curso
     * @param id
     * @return
     */
    @GetMapping("/find-usuarios-by-curso/{id}")
    public ResponseEntity<?> findUsuariosByCursoId(@PathVariable("id") Long id){
      List<UsuarioDTO> list = this.cursoUsuarioService.findUsuariosByCursoId(id);
      return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/list-curso-usuario")
    public ResponseEntity<?> findCursoUsuario(){
        List<CursoUsuarioDTO> list = this.cursoUsuarioService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
