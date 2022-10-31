package com.hoaxify.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoaxify.models.User;
import com.hoaxify.repository.UserRepository;

@Service
public class UserService {

	UserRepository userRepository;
	
	BCryptPasswordEncoder passwordEnconder;
	
	UserService(UserRepository userRepository) {
		this.userRepository =  userRepository;
		this.passwordEnconder = new BCryptPasswordEncoder();
	}
	
	public void save(User user) {
		user.setPassword(passwordEnconder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	
	
}
