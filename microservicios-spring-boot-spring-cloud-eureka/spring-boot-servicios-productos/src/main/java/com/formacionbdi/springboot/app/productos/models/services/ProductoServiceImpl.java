package com.formacionbdi.springboot.app.productos.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.formacionbdi.springboot.app.productos.models.repository.ProductoRepository;
import com.spring.app.commons.models.entity.Producto;


@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	ProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	@Transactional
	public Producto findById(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public void deleteById(Long id) {
	  productoRepository.deleteById(id);
	}

}
