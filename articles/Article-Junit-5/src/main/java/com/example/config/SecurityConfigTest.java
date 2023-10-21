package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("test")
public class SecurityConfigTest {

  @Bean
    public SecurityFilterChain testSecuirityFilterChain(HttpSecurity http) throws Exception {
      return http.csrf(csrfConfigurer -> {
                 csrfConfigurer.disable();
              })
              .authorizeHttpRequests( auth -> {
                auth.requestMatchers("/**").permitAll();
              }).build();
  }
}
