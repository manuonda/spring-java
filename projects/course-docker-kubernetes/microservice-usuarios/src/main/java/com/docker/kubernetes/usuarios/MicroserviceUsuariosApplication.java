package com.docker.kubernetes.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUsuariosApplication.class, args);
	}

}
