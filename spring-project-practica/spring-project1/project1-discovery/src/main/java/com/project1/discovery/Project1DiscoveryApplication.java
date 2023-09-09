package com.project1.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Project1DiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project1DiscoveryApplication.class, args);
	}

}
