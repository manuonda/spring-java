package com.springboot.webflux.client.service;


import org.springframework.http.codec.multipart.FilePart;

import com.springboot.webflux.client.models.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductoService {
	 Flux<Producto> findAll();
	   
	   Flux<Producto> findAllConNombreUpperCase();

	   Flux<Producto> findAllConNombreUpperCaseRepeat();

	   
	   Mono<Producto> findById(String id);
	   
	   Mono<Producto> save(Producto producto);
	   
	   
	   Mono<Producto> findByNombre(String nombre);
	   
	   Mono<Producto> update(Producto producto, String id);

	   Mono<Void> delete(String id);
	   
	   Mono<Producto> upload(FilePart file, String id);
}
