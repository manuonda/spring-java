package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.services.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



@RestController
@RequestMapping("/api/items")
public class ItemController {
	
	private Logger logger= LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private CircuitBreakerFactory cbFactory;

	@Autowired
	// con el qualifier me permite indicar que servicio trabajar
	@Qualifier("serviceFeign")
	private ItemService itemService;
 
	
	
	@GetMapping("/")
	public String home() {
		return "hola mundo";
	}
	@GetMapping("/listar")
	public List<Item> listar(
			@RequestParam(name="nombre" , required=false) String nombre, 
			@RequestHeader(name="token-request", required =  false) String token
	 ){
		System.out.println("Nombre : " + nombre);
		System.out.println("token-request: " +token);
		return itemService.findAll();
	}
	
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item itemDetalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		// cbFactory : el 2do parametro llamara al error y le pasa como argumento un metodo alternativo
		return cbFactory.create("items")
				.run(() -> itemService.findById(id, cantidad), e ->metodoAlternativo(id, cantidad, e ));
	}
	
	

	public Item metodoAlternativo(Long id, Integer cantidad, Throwable e ) {
		logger.info(e.getMessage());
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return item;
	}
}
