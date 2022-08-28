package com.formacionbdi.springboot.app.productos.models.services;

import java.util.List;

import com.formacionbdi.springboot.app.productos.models.entity.Producto;

public interface IProductoService {
 
	List<Producto> findAll();
	Producto findById(Long id);
	
	Producto save(Producto producto);
	void deleteById(Long id);
	
}
