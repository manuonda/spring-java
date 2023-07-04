package com.springboot.webflux.service;

import com.springboot.webflux.models.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
   Flux<Producto> findAll();
   
   Flux<Producto> findAllConNombreUpperCase();

   Flux<Producto> findAllConNombreUpperCaseRepeat();

   
   Mono<Producto> findById(String id);
   
   Mono<Producto> save(Producto producto);
   
   Mono<Void> delete(Producto producto);
   
   Mono<Producto> findByNombre(String nombre);
   
}
