package com.gateway.config;

import java.time.Duration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.factory.FallbackHeadersGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerResilience4JFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.DispatcherHandler;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class GateWayConfig {

	@Autowired 
	private AuthFilter authFilter;
	
	@Bean
	@Profile("localhostRouter-no-eureka")
	public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/api/v1/dragonball/*").uri("http://localhost:8082"))
				.route(r -> r.path("/api/v1/gameofthrones/*").uri("http://localhost:8083"))
				
				.build();
	}
	@Bean
	@Profile("localhost-eureka")
	public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/api/v1/dragonball/*").uri("lb://development"))
				.route(r -> r.path("/api/v1/gameofthrones/*").uri("lb://spring-client-gameofthrones"))
				
				.build();
	}
	
	@Bean
	@Profile("localhost-eureka-cb")
	public RouteLocator configLocalEurekaCB(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( r -> r.path("/api/v1/dragonball/**")
						.filters(f ->{
							f.circuitBreaker( c ->c.setName("failoverCB")
							  .setFallbackUri("forward:/api/v1/db-failover/dragonball/characters")
							  .setRouteId("dbFailover"));
							f.filter(authFilter);
							 return f;
						})
						.uri("lb://development"))
				.route(r -> r.path("/api/v1/db-failover/dragonball/*").uri("lb://spring-client-dragonball-failover"))
				.route(r -> r.path("/api/v1/gameofthrones/*")
						.filters( f -> f.filter(authFilter))
						.uri("lb://spring-client-gameofthrones"))
				.route(r -> r.path("/auth/**")
						.uri("lb://spring-authentication"))
				
				.build();
	}
	
	

}
