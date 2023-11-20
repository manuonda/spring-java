package com.project.two.inventario.presentation.controller;


import com.project.two.inventario.domain.dto.InventarioDTO;
import com.project.two.inventario.business.service.InventarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
