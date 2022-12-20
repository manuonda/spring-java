package com.eazybytes.springsecuritybassic.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		/**
		 * Configuration deny all request
		 
	    return http.authorizeRequests().anyRequest().denyAll()
	    		.and().formLogin()
	    		.and().httpBasic()
	    		.and().build();
         **/
		
		/**
		 * Permite all 
		 
	    return http.authorizeRequests().anyRequest().permitAll()
	    		.and().httpBasic().and().formLogin().and().build();
	    */
		
	    return http.authorizeRequests()
	   .antMatchers("/account","/balance","/loans","/cards").authenticated()
	   .antMatchers("/notices","/contact").permitAll()
	   .and()
	   .httpBasic()
	   .and()
	   .formLogin()
	   .and()
	   .build();
	    

	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("123465")
				.authorities("admin")
				.build();
	
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("12345")
				.authorities("read")
				.build();
		return new InMemoryUserDetailsManager(admin , user );
				
	}
	
}
