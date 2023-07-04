package com.springboot.webflux.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.webflux.models.Producto;

import reactor.core.publisher.Mono;

public interface IProductoDAO  extends ReactiveMongoRepository<Producto, String>{
   
	public Mono<Producto> findByNombre(String nombre);
	
	
	@Query("{ 'nombre' :?0}")
	public Mono<Producto> getByNombre(String nombre);
}
