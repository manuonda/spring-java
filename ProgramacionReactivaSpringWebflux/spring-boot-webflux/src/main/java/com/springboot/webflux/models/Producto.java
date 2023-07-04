package com.springboot.webflux.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection="productos")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Producto {

	@Id
	private String id;
	
	
	private String nombre;

	private Double precio;
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date createAt;
	
	Categoria categoria;
	
	private String foto;
	
	
	public Producto(String nombre, Double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}


	public Producto(String nombre, Double precio, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}
	

}
