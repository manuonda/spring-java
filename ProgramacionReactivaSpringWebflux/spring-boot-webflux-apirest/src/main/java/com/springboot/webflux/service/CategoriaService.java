package com.springboot.webflux.service;

import com.springboot.webflux.models.Categoria;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaService {

	Flux<Categoria> findAll();
	
	Mono<Categoria> findById(String id);
	
	Mono<Categoria> save(Categoria categoria);
	
	Mono<Categoria> findByNombre(String nombre);
	
}
