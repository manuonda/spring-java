package com.example.config;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

@Configuration
public class    JwtAuthProviderFilter extends OncePerRequestFilter {

    @Value("${secret.key}")
    private String secretKey = "123o1321290123j12ljh12iou";



    //@Autowired
    /*private  JWTAuthProvider jwtAuthProvider;


    /*

    public JwtAuthProviderFilter(JWTAuthProvider jwtAuthProvider) {
        this.jwtAuthProvider = jwtAuthProvider;
    }*/

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
          Authentication authentication = validateToken(token);
         SecurityContextHolder.getContext().setAuthentication(authentication);
     }
     filterChain.doFilter(request, response);
     return;

    }


    public Authentication validateToken (String token) {
        System.out.println("SecretKey antes : " + secretKey);
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        Algorithm algorithm  = Algorithm.HMAC256(secretKey);
        JWTVerifier jwtVerifier = com.auth0.jwt.JWT.require(algorithm).build();
        DecodedJWT decoded = jwtVerifier.verify(token);

        UserDTO userDTO =  UserDTO.builder()
                .login(decoded.getIssuer())
                .username(decoded.getClaim("userName").toString())
                .build();

        return new UsernamePasswordAuthenticationToken(userDTO, null , Collections.emptyList());

    }


}
