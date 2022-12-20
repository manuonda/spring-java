package com.spring.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.spring.app.commons.usuarios.entity"})
public class SpringBootServiciosUsuarios3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServiciosUsuarios3Application.class, args);
	}

}
