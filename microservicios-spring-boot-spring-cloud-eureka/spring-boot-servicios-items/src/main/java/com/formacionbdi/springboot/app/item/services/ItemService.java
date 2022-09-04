package com.formacionbdi.springboot.app.item.services;

import java.util.List;

import com.formacionbdi.springboot.app.item.models.Item;
import com.spring.app.commons.models.entity.Producto;


public interface ItemService {
 
	public List<Item> findAll();
	Item findById(Long id, Integer cantidad);
	
	Producto save(Producto producto);
	Producto update(Producto producto , Long id);
	void delete(Long id);
}
