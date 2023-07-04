package com.springboot.webflux.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.springboot.webflux.client.handler.ProductoHandler;

@Configuration
public class RouterConfigClient {

	@Bean
	public RouterFunction<ServerResponse> routerConfig(ProductoHandler handler) {
		return RouterFunctions.route
				( RequestPredicates.GET("/api/client"), handler::listar)
				.andRoute(RequestPredicates.GET("/api/client/{id}"), handler::ver)
				.andRoute(RequestPredicates.POST("/api/client"), handler::crear)
				.andRoute(RequestPredicates.DELETE("/api/client/{id}"), handler::delete)
				.andRoute(RequestPredicates.PUT("/api/client/{id}"), handler::update)
				.andRoute(RequestPredicates.POST("/api/client/upload/{id}"), handler::upload);
	}
	
}
