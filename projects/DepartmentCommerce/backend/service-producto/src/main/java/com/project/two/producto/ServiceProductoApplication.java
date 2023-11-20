package com.project.two.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductoApplication.class, args);
	}

}


// Redis :  https://medium.com/nerd-for-tech/event-driven-architecture-with-redis-streams-using-spring-boot-a81a1c9a4cde
// Videitos: https://www.youtube.com/watch?v=50baJDQAnDs&list=PLRt8BCu0IlVvB3QwjE8nI2pupZ10rK38T
// Videito 2 :  https://www.youtube.com/watch?v=oRGqCz8OLcM

// TODO : Probar los controllers de Categoria
// TODO: Realizar los controllers de Producto
// TODO: Agregar validation para crear y mostrar los errores
// TODO : Agregar Redis configuration si se puede .
// TODO: Armar pequeño diagrama de como se estaria manejando la application
// TODO: Terminar los test de Producto y Test capa Controller
// TODO: Agregar Swagger
// TODO : Agregar Observabily