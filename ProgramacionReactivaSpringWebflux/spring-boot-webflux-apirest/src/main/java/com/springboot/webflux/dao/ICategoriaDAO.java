package com.springboot.webflux.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.webflux.models.Categoria;

import reactor.core.publisher.Mono;

public interface ICategoriaDAO extends ReactiveMongoRepository<Categoria, String> {

	 Mono<Categoria> findByNombre(String nombre);
}
