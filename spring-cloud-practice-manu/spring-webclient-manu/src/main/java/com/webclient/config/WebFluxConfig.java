package com.webclient.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebFluxConfig implements WebFluxConfigurer{

	
	@Bean
	public WebClient getWebClient(WebClient.Builder webClientBuilder) {
	    return webClientBuilder
	        .baseUrl("http://localhost:8083")
	        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	        .build();
	}
	
}
