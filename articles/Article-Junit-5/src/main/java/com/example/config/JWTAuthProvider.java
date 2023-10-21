package com.example.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.domain.User;
import com.example.dto.UserDTO;
import com.example.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.function.Function;

import static org.springframework.security.config.Elements.JWT;

@Component
public class JWTAuthProvider {

    @Value("${secret.key}")
    private String secretKey;

    @PostConstruct
    public void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDTO userDTO){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000) ;

        String token = com.auth0.jwt.JWT.create()
                .withIssuer(userDTO.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("userName", userDTO.getUsername())
                .withClaim("lastName", userDTO.getLastName())
                .sign(Algorithm.HMAC256(secretKey));
        return token;

    }

    public Authentication validateToken (String token) {
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
