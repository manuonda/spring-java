package com.formacionbdi.springboot.app.productos.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.commons.models.entity.Producto;



public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
