package com.project1.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class Project1ConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project1ConfigserverApplication.class, args);
	}

}
