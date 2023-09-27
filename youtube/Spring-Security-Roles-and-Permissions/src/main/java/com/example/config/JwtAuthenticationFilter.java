package com.example.config;

import com.example.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JWTService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal
            (HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain) throws ServletException, IOException {
       final String authHeader = request.getHeader("Authorization");
       final String jwt;
       final String userEmail;
       if ( authHeader == null  || !authHeader.startsWith("Bearer ")) {
           filterChain.doFilter(request,response);
           return;
       }
       // Start 7 because for the world :  "Bearer "
        // get token cifrado
       jwt = authHeader.substring(7);
       userEmail = jwtService.extractUsername(jwt);
       if ( userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           // from database
           UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
           if(jwtService.isTokenValid(jwt, userDetails)) {
               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                  userDetails,
                  null,
                  userDetails.getAuthorities()
               );
               authenticationToken.setDetails(
                       new WebAuthenticationDetailsSource().buildDetails(request)
               );
               // Update Context
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);

           }
       }
       filterChain.doFilter(request, response);
       return;
    }
}
