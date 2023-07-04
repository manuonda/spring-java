package com.springboot.webflux.client.handler;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.springboot.webflux.client.models.Producto;
import com.springboot.webflux.client.service.IProductoService;

import reactor.core.publisher.Mono;

@Component 
public class ProductoHandler {

	@Autowired 
	private IProductoService productoService;
	
	public Mono<ServerResponse> listar(ServerRequest request) {
		
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(this.productoService.findAll(),Producto.class);
	}
	
	
	public Mono<ServerResponse> ver(ServerRequest request) {
		String id = request.pathVariable("id");
		return  errorHandler(this.productoService.findById(id).flatMap( p -> 
				ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(p))
				.switchIfEmpty(ServerResponse.notFound().build()));
	}
	
	
	public Mono<ServerResponse> crear(ServerRequest request) {
	  Mono<Producto> producto = request.bodyToMono(Producto.class);
	  
	 return producto.flatMap( p -> {
		return this.productoService.save(p); 
	 }).flatMap(p ->  
			     ServerResponse.created(URI.create("/api/client/".concat(p.getId())))
			     .contentType(MediaType.APPLICATION_JSON)
			     .bodyValue(p))
			  .onErrorResume(error -> {
				  WebClientResponseException errorResponse = (WebClientResponseException) error;
				  if ( errorResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
					 return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON)
							         .bodyValue(errorResponse.getResponseBodyAsString());
				  }
				  
				  return Mono.error(errorResponse);
			  });

	}
	
	
	public Mono<ServerResponse> update(ServerRequest request) {
		Mono<Producto> producto = request.bodyToMono(Producto.class);
		String id = request.pathVariable("id");	
		
		return 
			 errorHandler( producto.flatMap(p -> this.productoService.update(p, id))
			 .flatMap( p -> 
		      ServerResponse.created(URI.create("/api/client/".concat(id)))
		     .contentType(MediaType.APPLICATION_JSON)
		     .bodyValue(p)));
	}
	
	
	public Mono<ServerResponse> delete(ServerRequest request) {
	   String id = request.pathVariable("id");
	   
	   return  errorHandler(
			   this.productoService.delete(id).then(ServerResponse.noContent().build())
			   );
			  
	
	}
	
	public Mono<ServerResponse> upload(ServerRequest request) {
		String id = request.pathVariable("id");
		
		return  errorHandler(request.multipartData().map( multipart -> multipart.toSingleValueMap().get("file"))
				.cast(FilePart.class)
				.flatMap(file -> this.productoService.upload(file, id))
				.flatMap( p -> ServerResponse.created(URI.create("/api/client/".concat(p.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.bodyValue(p)));
				
				
	}
	
	
	/**
	 * Error Handler General
	 * @param response
	 * @return
	 */
	public Mono<ServerResponse> errorHandler(Mono<ServerResponse> response) {
		return response.onErrorResume(error -> {
			  WebClientResponseException errorResponse = (WebClientResponseException) error;
			  if ( errorResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				 Map<String , Object> body = new HashMap<>();
				 body.put("error", "No existe el producto : ".concat(errorResponse.getMessage()));
				 body.put("timestamp", new Date());
				 body.put("status", errorResponse.getStatusCode());
				 return ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(body);
			  }
			  
			  return Mono.error(errorResponse);
		  });
	}
}
