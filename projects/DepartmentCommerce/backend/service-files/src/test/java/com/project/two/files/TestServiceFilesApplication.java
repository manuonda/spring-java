package com.project.two.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestServiceFilesApplication {

	public static void main(String[] args) {
		SpringApplication.from(ServiceFilesApplication::main).with(TestServiceFilesApplication.class).run(args);
	}

}
