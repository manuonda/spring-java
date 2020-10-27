package com.manuonda.dependency.injection.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Coche {

	private String marca;

	private Integer modelo;

	private Motor motor;

	public Coche() {
		super();
	}

	@Autowired
	public Coche(@Value("VW") String marca, @Value("1981") Integer modelo, Motor motor) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
	}

	@Override
	public String toString() {
		return "Coche [marca=" + marca + ", motor=" + motor + "]";
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

}
