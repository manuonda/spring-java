package com.manuonda.dependency.injection.qualifiers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Perro extends Animal{

	public Perro(@Value("23")Integer edad,@Value("Perro Rocky") String nombre) {
		super(edad, nombre);
	}
	
}
