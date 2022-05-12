package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.services.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService iProductoService;
	
	@GetMapping("/list")
	public List<Producto> getAll() {
		return iProductoService.findAll();
	}
	
	@GetMapping("/{id}")
	public Producto getById(@PathVariable Long id) {
		return iProductoService.findById(id);
	}
}
