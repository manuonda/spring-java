package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.item.models.Producto;

@FeignClient(name ="servicio-productos" , url="localhost:8001")
public interface ProductoClientRest {
 
	@GetMapping("/listar")
	List<Producto> listar();
	
	@GetMapping("/{id}")
	Producto detalle(@PathVariable Long id);
 	
}
