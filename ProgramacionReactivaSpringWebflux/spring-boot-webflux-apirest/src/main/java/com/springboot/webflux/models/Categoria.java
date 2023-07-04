package com.springboot.webflux.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection="categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Categoria {

	@Id
	private String id;
	private String nombre;
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	
}
