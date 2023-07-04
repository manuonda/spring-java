package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringServerManuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServerManuApplication.class, args);
	}

}
