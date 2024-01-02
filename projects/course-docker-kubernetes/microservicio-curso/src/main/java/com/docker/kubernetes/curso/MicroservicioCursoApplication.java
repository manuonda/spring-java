package com.docker.kubernetes.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicioCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCursoApplication.class, args);
	}

}
