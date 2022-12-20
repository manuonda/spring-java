package com.spring.app.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  
	
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        return http.csrf()
	        .disable()
	        .authorizeRequests()
	        .anyRequest()
	        .authenticated()
	        .and()
	        .httpBasic()
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .build();
	    }
	  
	  
	  @Bean 
	  PasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder();
	  }
	  
	  @Bean
	  UserDetailsService userDetailService() {
		  InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		  manager.createUser(User.withUsername("admin")
				  .password(passwordEncoder().encode("admin"))
				  .roles()
				  .build());
	     return manager;
		  
	  }
	  
	  
	
	  
	
}
