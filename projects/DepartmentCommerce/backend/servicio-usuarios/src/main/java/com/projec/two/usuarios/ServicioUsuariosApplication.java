package com.projec.two.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicioUsuariosApplication {

	// TODO: realizar una registration de usuairo y enviar
	// un email para activar el usuario y tambien
	// subir la imagen a S3
	// la comunicacion se realiza con el enviom de email kafka s3
	// la comunicacion de subidad de archivo usa openfeign
	public static void main(String[] args) {
		SpringApplication.run(ServicioUsuariosApplication.class, args);
	}

}
