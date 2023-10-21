package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class SecurityConfig {

    /*
    private final JWTAuthProvider jwtAuthProvider;

    public SecurityConfig(JWTAuthProvider jwtAuthProvider) {
        this.jwtAuthProvider = jwtAuthProvider;
    }

*/

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/login","/api/v1/auth/register").permitAll()
                            .anyRequest().authenticated();

                })
                .addFilterBefore(new JwtAuthProviderFilter(), BasicAuthenticationFilter.class);

         return http.build();
     }


  /*
   Spring Security’s InMemoryUserDetailsManager implements UserDetailsService
   to provide support for username/password based authentication that is stored in memory
   */
   @Bean
    public UserDetailsService userDetails() {
      UserDetails user = User.builder()
              .username("manuonda")
              .password("{noop}123456")
              .build();
      return  new InMemoryUserDetailsManager(user);
   }

}
