package com.eazybytes.springsecuritybassic.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.eazybytes.springsecuritybassic.filter.AuthoritiesLoggingAfterFilter;
import com.eazybytes.springsecuritybassic.filter.AuthoritiesLoggingAtFilter;
import com.eazybytes.springsecuritybassic.filter.JWTTokenGeneratorFilter;
import com.eazybytes.springsecuritybassic.filter.JWTTokenValidatorFilter;
import com.eazybytes.springsecuritybassic.filter.RequestValidationBeforeFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	
	UserDetailsService userDetailsService;
	
	UserDetailsManager userDetailsManager;
	
	JdbcUserDetailsManager jdbc;
	
	UserDetails userDetails;
	
	
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
		
//	    return http
//	    		.sessionManagement()
//	    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	    		.and()
//	    		.cors()
//	    		.configurationSource(new CorsConfigurationSource() {
//					
//					@Override
//					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//						CorsConfiguration config= new CorsConfiguration();
//						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//						config.setAllowedMethods(Collections.singletonList("*"));
//						config.setAllowCredentials(true);
//						config.setAllowedHeaders(Collections.singletonList("*"));
//						config.setExposedHeaders(Arrays.asList("Authorization"));
//						config.setMaxAge(3600L);
//						return config;
//					}
//				 })
//	    		 .and()
//	    		 .csrf().ignoringAntMatchers("/contact","/register").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//	    		 .and()
//	    		 .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
//	                .addFilterAt(new AuthoritiesLoggingAtFilter(),BasicAuthenticationFilter.class)
//	                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
//	                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//	                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
//	    		 .authorizeRequests()
////	    		 .antMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
////	    		 .antMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
////	    		 .antMatchers("/myLoans").hasAuthority("VIEWLOANS")
////	    		 .antMatchers("/myCards").hasAuthority("VIEWCARDDETIALS")
//	    		 .antMatchers("/myAccount").hasRole("USER")
//	    		 .antMatchers("/myBalance").hasAnyRole("USER","ADMIN")
//	    		 .antMatchers("/myLoans").hasRole("USER")
//	    		 .antMatchers("/myCards").hasRole("USER")
//	             .antMatchers("/user").authenticated()
//	             .antMatchers("/notices/**","/contact/**","/register/**").permitAll()
//	             .and()
//	             .httpBasic()
//	             .and()
//	             .formLogin()
//	             .and()
//	             .build();
		
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
         .cors().configurationSource(new CorsConfigurationSource() {
         @Override
         public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
             CorsConfiguration config = new CorsConfiguration();
             config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
             config.setAllowedMethods(Collections.singletonList("*"));
             config.setAllowCredentials(true);
             config.setAllowedHeaders(Collections.singletonList("*"));
             config.setExposedHeaders(Arrays.asList("Authorization"));
             config.setMaxAge(3600L);
             return config;
         }
     }).and().csrf().ignoringAntMatchers("/contact","/register").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
             .and()
             //.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
             .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
             .addFilterAt(new AuthoritiesLoggingAtFilter(),BasicAuthenticationFilter.class)
             .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
             .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
             .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
             .authorizeHttpRequests()
                     .antMatchers("/myAccount").hasRole("USER")
                     .antMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                     .antMatchers("/myLoans").hasRole("USER")
                     .antMatchers("/myCards").hasRole("USER")
                     .antMatchers("/user").authenticated()
                     .antMatchers("/notices","/contact","/register").permitAll()
             .and().formLogin()
             .and().httpBasic();
     return http.build();
	}
	
	private CorsConfiguration corsConfigurationSource(HttpServletRequest request) {
		CorsConfiguration config= new CorsConfiguration();
		config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Collections.singletonList("*"));
		config.setMaxAge(3600L);
		return config;
	}
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		/*Approach with DefaultPassworEncoder
//		 * UserDetails admin = User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("123465")
//				.authorities("admin")
//				.build();
//	
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("12345")
//				.authorities("read")
//				.build();
//		return new InMemoryUserDetailsManager(admin , user );
//		*/
//		
//		UserDetails admin = User.withUsername("admin")
//				.password("123465")
//				.authorities("admin")
//				.build();
//	
//		UserDetails user = User.withUsername("user")
//				.password("12345")
//				.authorities("read")
//				.build();
//		return new InMemoryUserDetailsManager(admin, user);
//	}
	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}
//	
	
	/*@Bean 
	public UserDetails userDetailsService() {
	
	}*/
	
	
	/** Sin Password encoder
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	**/ 
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
