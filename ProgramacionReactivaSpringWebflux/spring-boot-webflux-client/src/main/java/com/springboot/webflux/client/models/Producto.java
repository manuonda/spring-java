package com.springboot.webflux.client.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Producto {

	private String id;
	private String nombre;
    private Double precio;
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date createAt;
	Categoria categoria;
	private String foto;
}
