package com.authentication.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.authentication.config.JWTProvider;
import com.authentication.models.dto.TokenDTO;
import com.authentication.models.dto.UserDTO;
import com.authentication.models.entity.UserEntity;
import com.authentication.repository.UserRepository;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

@Service
public class AuthService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JWTProvider jwtProvider;
	
	
	public UserDTO save(UserDTO user) {
		Optional<UserEntity> response = this.userRepository.findByUsername(user.getUsername());
		if(response.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User % already exist", user.getUsername()));
		}
		UserEntity entity = this.userRepository.save(new UserEntity(null, user.getUsername(), encoder.encode(user.getPassword())));
		return mapper.map(entity, UserDTO.class);
	}
	
	public TokenDTO login(UserDTO user) {
		
		UserEntity userEntity = this.userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
		if(encoder.matches(user.getPassword(), userEntity.getPassword())) {
			return new TokenDTO(this.jwtProvider.createToken(userEntity));
		} else { 
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}
	

	public TokenDTO validate(String token) {
		jwtProvider.validate(token);
		String username =  jwtProvider.getUserName(token);
		UserEntity result = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
		return new TokenDTO(token);
	}
}
