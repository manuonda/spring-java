package com.manuonda.dependency.injection.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("pajarito")
public class Pajaro extends Animal implements Volador{
	
	private static final Logger log =  LoggerFactory.getLogger(Pajaro.class);

	public Pajaro(@Value("12") Integer edad,@Value("QuendoPajaro") String nombre) {
		super(edad, nombre);
	}

	
	@Override
	public void volar() {
		log.info("Soy un pajaro y estoy volando");
	}

}
