package com.project.two.producto.controller;


import com.project.two.producto.dto.CategoriaDTO;
import com.project.two.producto.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/categoria")
@RestController
public class CategoriaController {

   private final CategoriaService categoriaService;


    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoriaDTO>> listAll(){
        List<CategoriaDTO> list = this.categoriaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable("id") Long id) {
        CategoriaDTO categoriaDTO = this.categoriaService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(categoriaDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO dto){
       CategoriaDTO saveCategoria = this.categoriaService.save(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(saveCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable("id") Long id , @Valid @RequestBody CategoriaDTO categoria) {
        CategoriaDTO updateCategoria = this.categoriaService.update(categoria, id);
        return ResponseEntity.status(HttpStatus.OK).body(updateCategoria);
    }
}
