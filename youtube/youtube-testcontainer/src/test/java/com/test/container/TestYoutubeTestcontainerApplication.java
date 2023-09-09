package com.test.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestYoutubeTestcontainerApplication {

	public static void main(String[] args) {
		SpringApplication.from(YoutubeTestcontainerApplication::main).with(TestYoutubeTestcontainerApplication.class).run(args);
	}

}
