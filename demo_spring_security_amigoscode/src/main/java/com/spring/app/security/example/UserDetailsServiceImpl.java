package com.spring.app.security.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
			new User( "manuonda","123465",Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
					),
			new User("dgarcia","123456789",Collections.singleton(new SimpleGrantedAuthority("ADMIN")))
		);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		return APPLICATION_USERS.stream().filter( u -> u.getUsername().equals(username))
			.findFirst()
			.orElseThrow(() -> new UsernameNotFoundException("No user found"));
	}

}
