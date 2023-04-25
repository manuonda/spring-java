package com.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.authentication.models.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class JWTProvider {

	@Value("${jwt.secret}")
	private String token;
	
	private String createToken(UserEntity user) {
	    Claims setSubject = Jwts.claims().setSubject(user.getUsername());
		return null;
	}
}
