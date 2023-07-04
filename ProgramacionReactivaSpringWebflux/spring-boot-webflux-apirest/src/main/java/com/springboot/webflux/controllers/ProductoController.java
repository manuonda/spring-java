package com.springboot.webflux.controllers;

import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.springboot.webflux.models.Producto;
import com.springboot.webflux.service.ProductoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired 
	private ProductoService productoService;
	
	private String path = "/home/manuonda/Documents/";
	
	
	@GetMapping("/listar")
	public Mono<ResponseEntity<Flux<Producto>>> getAll(){
	      return Mono.just(
	    		  ResponseEntity.ok()
	    		  .contentType(MediaType.APPLICATION_JSON_UTF8)
	    		  .body(this.productoService.findAll())
	    		  );
	}
	
	@GetMapping("{id}")
	public Mono<ResponseEntity<Producto>> getProducto(@PathVariable String id) {
		return this.productoService.findById(id)
				.map( p -> ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<Producto>> save(@RequestBody Producto producto) {
        if (producto.getCreateAt() == null ) {
        	producto.setCreateAt(new Date());
        }
        
        return this.productoService.save(producto).map(p -> 
            ResponseEntity.created(URI.create("/api/productos/".concat(p.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(producto));
        		
	}
	
	@PostMapping("/crear/validate")
	public Mono<ResponseEntity<Map<String, Object>>> saveValidate(@Valid @RequestBody Mono<Producto> monoProducto) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
		return monoProducto.flatMap( producto -> {
			if (producto.getCreateAt() == null ) {
	        	producto.setCreateAt(new Date());
	        }
			
			  return this.productoService.save(producto).map(p -> {
				  respuesta.put("producto", p);
				  respuesta.put("message","Producto Creado con Exito" );
				  respuesta.put("timestamp", new Date());
				  return ResponseEntity.created(URI.create("/api/productos/".concat(p.getId())))
		            .contentType(MediaType.APPLICATION_JSON)
		            .body(respuesta);  
			  });
	            
		}).onErrorResume(t -> {
			   return Mono.just(t).cast(WebExchangeBindException.class)
					   .flatMap( e -> Mono.just(e.getFieldErrors()))
					   .flatMapMany( errors -> Flux.fromIterable( errors ))
					   .map(fieldError -> "El Campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
					   .collectList()
					   .flatMap( list -> {
						   respuesta.put("errors", list);
						   respuesta.put("timestamp", new Date());
                           respuesta.put("status", HttpStatus.BAD_REQUEST.value());

						   return Mono.just(ResponseEntity.badRequest().body(respuesta));
					   });
		});
		
		
        
      
        		
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Producto>> update(@RequestBody Producto producto, String id) {
		return this.productoService.findById(id)
				.flatMap(p -> {
					p.setCategoria(producto.getCategoria());
					p.setNombre(producto.getNombre());
					p.setPrecio(producto.getPrecio());
					return productoService.save(p);
				})
				.map( p -> 
				    ResponseEntity.created(URI.create("/api/productos/".concat(p.getId())))
				    .contentType(MediaType.APPLICATION_JSON)
				    .body(p)
				   )
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteProducto(@PathVariable String id) {
		return this.productoService.findById(id).flatMap( p -> {
			return this.productoService.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/upload/{id}")
	public Mono<ResponseEntity<Producto>> upload(@PathVariable String id, FilePart file) {
		return this.productoService.findById(id)
				.flatMap( p -> {
					p.setFoto(UUID.randomUUID().toString() + "-"+file.filename()
					.replace(" ","")
					.replace(":","")
					.replace("",""));
					return file.transferTo(new File(path + p.getFoto())).then(this.productoService.save(p));
				})
				.map(p -> ResponseEntity.ok(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/upload/v2")
	public Mono<ResponseEntity<Producto>> upload2(Producto producto, FilePart file){
		if (producto.getCreateAt() == null  ) {
			producto.setCreateAt(new Date());
		}
		
		 producto.setFoto(UUID.randomUUID().toString() + "-"+file.filename()
		.replace(" ","")
		.replace(":","")
		.replace("",""));
		 
		 return  file.transferTo(new File(path + producto.getFoto())).then(
				 productoService.save(producto))
				 .map(p -> 
				          ResponseEntity.created(URI.create("/api/productos/".concat(p.getId())))
						 .contentType(MediaType.APPLICATION_JSON_UTF8)
						 .body(p)
				 );
				
	}
}
