package com.manuonda.dependency.injection.attribute;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Estereotipos por anotaciones
 * 
 * @author manuonda
 *
 */
@Component
public class Motor {

	@Override
	public String toString() {
		return "Motor [marca=" + marca + ", modelo=" + modelo + "]";
	}

	@Value("CL1")
	private String marca;

	private Integer modelo;
	
	public Motor() {
		
	}

//	public Motor( String marca,  Integer modelo) {
//		super();
//		this.marca = marca;
//		this.modelo = modelo;
//	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getModelo() {
		return modelo;
	}

	@Value("1982")
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

}
