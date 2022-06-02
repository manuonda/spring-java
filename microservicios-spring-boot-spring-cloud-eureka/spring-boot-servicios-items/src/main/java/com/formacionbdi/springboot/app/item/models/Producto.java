package com.formacionbdi.springboot.app.item.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

	private Long id;
	private String nombre;
	private Double precio;
	private Date createdAt;
	
	
}
