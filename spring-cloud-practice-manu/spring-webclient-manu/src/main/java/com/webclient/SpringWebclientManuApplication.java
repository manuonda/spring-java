package com.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringWebclientManuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebclientManuApplication.class, args);
	}

}
