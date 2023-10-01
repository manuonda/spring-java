package com.example.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class    JwtAuthProviderFilter extends OncePerRequestFilter {

    private final  JWTAuthProvider jwtAuthProvider;

    public JwtAuthProviderFilter(JWTAuthProvider jwtAuthProvider) {
        this.jwtAuthProvider = jwtAuthProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     String header = request.getHeader(HttpHeaders.AUTHORIZATION);
     if ( header == null || !header.startsWith("Bearer ")) {
         filterChain.doFilter(request, response);
         return;
     }


     // token
     String token =  header.substring(7);
     if ( token != null && !token.isEmpty()) {
          Authentication authentication = jwtAuthProvider.validateToken(token);
         SecurityContextHolder.getContext().setAuthentication(authentication);
     }
     filterChain.doFilter(request, response);
     return;

    }
}
