package com.project1.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Project1SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project1SchoolApplication.class, args);
	}

}
