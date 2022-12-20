package com.spring.app.security.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.security.example.model.Contacto;
import com.spring.app.security.example.repository.ContactoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("contactos")
@AllArgsConstructor
public class ContactoController {

	@Autowired
	private ContactoRepository contactoRepository;
	
	@GetMapping
	public List<Contacto> listContacto() {return contactoRepository.findAll();}
	
	
}
