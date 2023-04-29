package com.authentication.config;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.authentication.models.dto.TokenDTO;
import com.authentication.models.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTProvider {

	@Value("${jwt.secret}")
	private String secret;
	
	public String createToken(UserEntity user) {
	    Map<String, Object> claims = Jwts.claims().setSubject(user.getUsername());
	    claims.put("id", user.getId());
	    Date now = new Date();
	    Date exp = new Date(now.getTime() + 3600 * 1000 );
	    
	    
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256,secret)
				.compact();
	}
	
	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(secret)
			.parseClaimsJws(token);
			return true;	
		}catch(Exception ex ) {
			return false;
		}
		
	}
	
	public String getUserName(String token) {
		try {
			return Jwts.parser().setSigningKey(secret)
			.parseClaimsJws(token)
			.getBody().getSubject();
	
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token");
		}
	 }
	
	public void validate2(String token) {
		try {
			 Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	
}
