package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DanVegaGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DanVegaGatewayApplication.class, args);
	}


	@Bean
	public RouteLocator customRouterLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/posts/**")
						.filters(f -> f
								.prefixPath("/api/v1/")
								.addResponseHeader("X-Powered-By","David Garcia")
						).uri("http://localhost:8081")).build();

	}



}
