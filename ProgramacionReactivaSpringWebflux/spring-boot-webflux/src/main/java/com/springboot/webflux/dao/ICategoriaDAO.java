package com.springboot.webflux.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.webflux.models.Categoria;

public interface ICategoriaDAO extends ReactiveMongoRepository<Categoria, String> {

}
