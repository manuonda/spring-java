package com.project.two.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductoApplication.class, args);
	}

}


// Redis :  https://medium.com/nerd-for-tech/event-driven-architecture-with-redis-streams-using-spring-boot-a81a1c9a4cde

// TODO : Probar los controllers de Categoria
// TODO: Realizar los controllers de Producto
// TODO: Agregar validation para crear y mostrar los errores
// TODO : Agregar Redis configuration si se puede .
// TODO: Armar pequeño diagrama de como se estaria manejando la application