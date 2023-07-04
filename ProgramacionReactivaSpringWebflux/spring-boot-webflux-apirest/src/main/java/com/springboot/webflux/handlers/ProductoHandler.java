package com.springboot.webflux.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.springboot.webflux.models.Categoria;
import com.springboot.webflux.models.Producto;
import com.springboot.webflux.service.ProductoService;

import reactor.core.publisher.Mono;


import static org.springframework.web.reactive.function.BodyInserters.*;

import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Component
public class ProductoHandler {

	@Autowired
	private ProductoService productoService;
	
	
	private String path = "/home/manuonda/Documents/";
	
	
	public Mono<ServerResponse> listar(ServerRequest request){
		return ServerResponse.ok()
	             .contentType(MediaType.APPLICATION_JSON)
	             .body(this.productoService.findAll(), Producto.class);
	}
	
	
	public Mono<ServerResponse> ver(ServerRequest request) {
		String id = request.pathVariable("id");
		return this.productoService.findById(id)
				.flatMap( p -> 
				      ServerResponse
				      .ok()
				      .contentType(MediaType.APPLICATION_JSON)
				      .body(fromObject(p)))
				      .switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> crear(ServerRequest request) {
		Mono<Producto> producto = request.bodyToMono(Producto.class);
		return producto.flatMap(p -> {
			 if(p.getCreateAt() == null) {
				 p.setCreateAt(new Date());
			 }
			 
			 return this.productoService.save(p);
		}).flatMap( p -> 
		          ServerResponse.created(URI.create("/api/productos/v2/ver/".concat(p.getId())))
		          .contentType(MediaType.APPLICATION_JSON)
		          .body(fromObject(p)))
				  ;
	}
	
	
	public Mono<ServerResponse> editar(ServerRequest request) {
		Mono<Producto> producto = request.bodyToMono(Producto.class);
		String id = request.pathVariable("id");
		
		Mono<Producto> productoDb = this.productoService.findById(id);
		
		return productoDb.zipWith(producto,(db,req) -> {
		    	db.setNombre(req.getNombre());
		    	db.setPrecio(req.getPrecio());
		    	db.setCategoria(req.getCategoria());
		    	return db;
		}).flatMap( p -> 
		      ServerResponse.created(URI.create("/api/v2/productos/".concat(p.getId())))
		      .contentType(MediaType.APPLICATION_JSON)
		      .body(this.productoService.save(p), Producto.class)
			).switchIfEmpty(ServerResponse.notFound().build());
		
	}
	
	
	public Mono<ServerResponse> delete(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<Producto> monoProducto =  this.productoService.findById(id);
	
		return monoProducto.flatMap( p -> {
			return this.productoService.delete(p).then(
					  ServerResponse.noContent().build()
					);
		});  
	}
	
	
	public Mono<ServerResponse> uploadFile(ServerRequest request) {
		String id = request.pathVariable("id");
		return request.multipartData().map(multipart 
			 -> multipart.toSingleValueMap().get("file"))
		    .cast(FilePart.class)
		    .flatMap( file -> 
		         this.productoService.findById(id).flatMap(p -> {
		        	 
		            p.setFoto(UUID.randomUUID().toString() + " - " + file.filename()
		        	.replace(" ","")
					.replace(":","")
					.replace("",""));
		          
		            return file.transferTo(new File(path + p.getFoto())).then(productoService.save(p));
		            
		         })).flatMap( p -> 
		           ServerResponse.created(URI.create("/api/v2/productos/".concat(id)))
		           .contentType(MediaType.APPLICATION_JSON)
		           .body(fromObject(p)))
		           .switchIfEmpty(ServerResponse.notFound().build());
		    
		
	}
	
	
	public Mono<ServerResponse> crear2(ServerRequest request) {
		
		Mono<Producto> producto = request.multipartData().map(multipart 
				 ->  {
					 FormFieldPart nombre = (FormFieldPart) multipart.toSingleValueMap().get("nombre");
					 FormFieldPart precio = (FormFieldPart) multipart.toSingleValueMap().get("precio");
					 FormFieldPart categoriaId = (FormFieldPart) multipart.toSingleValueMap().get("categoriaId");
					 FormFieldPart categoriaNombre = (FormFieldPart) multipart.toSingleValueMap().get("categoriaNombre");
					 
					 Categoria categoria = new Categoria(categoriaNombre.value());
					 categoria.setId(categoriaId.value());
					 return new Producto(nombre.value(), Double.valueOf(precio.value()), categoria);
				 });
		
		return request.multipartData().map(multipart 
			 -> multipart.toSingleValueMap().get("file"))
		    .cast(FilePart.class)
		    .flatMap( file -> 
		          producto.flatMap(p -> {
		        	 
		            p.setFoto(UUID.randomUUID().toString() + " - " + file.filename()
		        	.replace(" ","")
					.replace(":","")
					.replace("",""));
		          
		            return file.transferTo(new File(path + p.getFoto())).then(productoService.save(p));
		            
		         })).flatMap( p -> 
		           ServerResponse.created(URI.create("/api/v2/productos/".concat(p.getId())))
		           .contentType(MediaType.APPLICATION_JSON)
		           .body(fromObject(p)));
		    
		
	}
	
}
