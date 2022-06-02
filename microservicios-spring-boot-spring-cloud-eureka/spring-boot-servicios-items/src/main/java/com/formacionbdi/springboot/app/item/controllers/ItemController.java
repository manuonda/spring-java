package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.services.ItemService;



@RestController
public class ItemController {

	@Autowired
	// con el qualifier me permite indicar que servicio trabajar
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	
	@GetMapping("/")
	public String home() {
		return "hola mundo";
	}
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item itemDetalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
}
