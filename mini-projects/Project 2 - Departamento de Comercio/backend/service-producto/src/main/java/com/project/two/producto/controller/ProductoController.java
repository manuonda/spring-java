package com.project.two.producto.controller;


import com.project.two.producto.dto.ProductoDTO;
import com.project.two.producto.service.ProductoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductoDTO>> getAll(){
        List<ProductoDTO> list= this.productoService.findAll();;
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable("id") Long id) {
        logger.info("Find by id : " + id);
        ProductoDTO  productoDTO = this.productoService.findById(id);
      return ResponseEntity.status(HttpStatus.FOUND).body(productoDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductoDTO> save(@RequestBody @Valid ProductoDTO productoDTO){
       logger.info("Save producto :"+ productoDTO.toString());
        ProductoDTO productoDTO1 = this.productoService.save(productoDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(productoDTO1);
    }


}
