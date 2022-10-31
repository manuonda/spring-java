package com.hoaxify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.access.SecurityConfig;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HoaxifyReactTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoaxifyReactTddApplication.class, args);
	}

}
