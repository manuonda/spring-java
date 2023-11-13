package com.project.two.inventario.controller;


import com.project.two.inventario.dto.InventarioDTO;
import com.project.two.inventario.service.InventarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventarios")
public class InventarioController {

    private  final InventarioService inventarioService;



    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/")
    public List<InventarioDTO> list() {
        return this.inventarioService.listAll();
    }

}
