package com.manuonda.dependency.injection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.manuonda.dependency.injection.attribute.Coche;
import com.manuonda.dependency.injection.qualifiers.Animal;
import com.manuonda.dependency.injection.qualifiers.Nido;
import com.manuonda.dependency.injection.qualifiers.Perro;

@SpringBootApplication
public class DependecyInjectionApplication {

	
	private static final Logger log = LoggerFactory.getLogger(DependecyInjectionApplication.class);

	public static void main(String[] args) {
	 ConfigurableApplicationContext context = SpringApplication.run(DependecyInjectionApplication.class, args);
     Coche coche = context.getBean(Coche.class); 
     Perro perro = context.getBean(Perro.class);
     
	 System.out.println(coche);
	 log.info("Soy un perro {}", perro.getNombre());
	
	 // Implementaion qualififers
	 Animal animal =  context.getBean("pajarito", Animal.class);
	 log.info("Animal nombre = {} , edad = {}", animal.getNombre(), animal.getEdad() );
	 
	 Nido nido = context.getBean(Nido.class);
     nido.imprimir();
	 
	}

}
