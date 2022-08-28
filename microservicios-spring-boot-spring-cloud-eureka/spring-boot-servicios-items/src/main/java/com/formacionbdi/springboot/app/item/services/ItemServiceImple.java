package com.formacionbdi.springboot.app.item.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;


@Service("serviceRestTemplate")
public class ItemServiceImple implements ItemService{

	
	@Autowired 
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		return productos.stream().map( producto -> new Item(producto , 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad ) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://localhost:8001/ver/{id}",Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		// se crea un exchange de productos
		// obtiene la respuesta en un tipo de Producto
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/create", HttpMethod.POST, body, Producto.class);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		// se crea un exchange de productos
		// obtiene la respuesta en un tipo de Producto
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/update/{id}", HttpMethod.PUT, body, Producto.class , pathVariables);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public void delete(Long id) {
		
		// se crea un exchange de productos
		// obtiene la respuesta en un tipo de Producto
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		 clienteRest.delete("http://servicio-productos/delete/{id}", pathVariables);
	}

	
}
