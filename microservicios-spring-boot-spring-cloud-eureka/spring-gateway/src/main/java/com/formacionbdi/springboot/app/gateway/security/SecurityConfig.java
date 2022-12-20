package com.formacionbdi.springboot.app.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;


/**
 * Class WebFlux COnfiguration
 * @author manuonda
 *
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	/**
	 * The ServerHttpSecurity is already preconfigured with some sane defaults, 
	 * so we could skip this configuration completely. 
	 * But for starters, we'll provide the following minimal config:
	 * @param http
	 * @return
	 */
	
	   @Bean
       SecurityWebFilterChain configure(ServerHttpSecurity http) {
    	   // minimal configuration 
		   return http.authorizeExchange()
				   .pathMatchers("/api/security/oauth").permitAll()
				   .pathMatchers(HttpMethod.GET,"/api/productos/listar",
						   "/api/items/listar",
						   "/api/usuarios/usuarios")
				   .permitAll()
				   .pathMatchers(HttpMethod.GET, "/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN","USER")
				   .anyExchange().authenticated()
				   .and().addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				   .csrf().disable()
				   .build();
       }
}
