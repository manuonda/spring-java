package com.docker.kubernetes.usuarios.msvc.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient //habilito para registrarse
@SpringBootApplication
public class MicroservicioAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAuthApplication.class, args);
	}

}
