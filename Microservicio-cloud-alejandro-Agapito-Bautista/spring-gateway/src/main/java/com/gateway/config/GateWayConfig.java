package com.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GateWayConfig {

	@Bean
	@Profile("localhostRouter-no-eureka")
	public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/api/v1/dragonball/*").uri("http://localhost:8082"))
				.route(r -> r.path("/api/v1/gameofthrones/*").uri("http://localhost:8083"))
				
				.build();
	}
	@
	Bean
	@Profile("localhost-eureka")
	public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/api/v1/dragonball/*").uri("lb://development"))
				.route(r -> r.path("/api/v1/gameofthrones/*").uri("lb://spring-client-gameofthrones"))
				
				.build();
	}
}
