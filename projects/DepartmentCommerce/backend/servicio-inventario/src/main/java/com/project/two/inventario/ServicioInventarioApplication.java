package com.project.two.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioInventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioInventarioApplication.class, args);
	}

}
