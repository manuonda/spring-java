package com.formacionbdi.springboot.app.item.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.ProductoClientRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;


@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

	
	@Autowired 
	private ProductoClientRest productoClientRest;
	
	
	@Override
	public List<Item> findAll() {
		return productoClientRest.listar().stream().map( producto -> new Item(producto , 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
	    Producto producto = productoClientRest.detalle(id);
		return new Item(producto, cantidad);

	}

	
}
