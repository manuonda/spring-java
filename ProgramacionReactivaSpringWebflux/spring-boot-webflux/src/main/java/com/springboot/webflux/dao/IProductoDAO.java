package com.springboot.webflux.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.webflux.models.Producto;

public interface IProductoDAO  extends ReactiveMongoRepository<Producto, String>{

}
