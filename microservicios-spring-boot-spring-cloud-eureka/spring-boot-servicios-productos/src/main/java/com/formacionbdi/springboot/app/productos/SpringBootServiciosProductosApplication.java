package com.formacionbdi.springboot.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.spring.app.commons.models.entity"})     //para buscar de otros projects
public class SpringBootServiciosProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServiciosProductosApplication.class, args);
	}

}
