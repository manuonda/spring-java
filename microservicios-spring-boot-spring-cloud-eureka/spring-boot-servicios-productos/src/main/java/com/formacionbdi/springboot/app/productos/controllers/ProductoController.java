package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.services.IProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private IProductoService iProductoService;
	
	
	
	@GetMapping("/")
	public String home() {
		return "hola mundo";
	}
	@GetMapping("/listar")
	public List<Producto> getAll() {
		return iProductoService.findAll();
	}
	
	@GetMapping("/{id}")
	public Producto getById(@PathVariable Long id) {
		return iProductoService.findById(id);
	}
	
	@GetMapping("/ver/{id}")
	public Producto details(@PathVariable Long id) throws InterruptedException {
		if (id.equals(10L)) {
			throw new IllegalStateException("Producto No Encontrado");
		}
		
		if( id.equals(7L)) {
			TimeUnit.SECONDS.sleep(5L);
		}
		
	    Producto producto = iProductoService.findById(id);
        return producto;		
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Producto producto) {
		Producto newProduct = this.iProductoService.save(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}
	
	@PutMapping("/update/{idProducto}")
	public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable("id") Long idProducto) {
		Producto updateProducto = this.iProductoService.findById(idProducto);
		updateProducto.setNombre(producto.getNombre());
		updateProducto.setPrecio(producto.getPrecio());
		Producto product = this.iProductoService.save(updateProducto);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@DeleteMapping("/delete/{idProducto}")
	public BodyBuilder delete(@PathVariable("idProducto") Long idProducto){
		this.iProductoService.deleteById(idProducto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT);
	}
}
