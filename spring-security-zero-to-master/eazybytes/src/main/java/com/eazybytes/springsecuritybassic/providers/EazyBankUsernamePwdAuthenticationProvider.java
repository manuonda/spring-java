package com.eazybytes.springsecuritybassic.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eazybytes.springsecuritybassic.models.Customer;
import com.eazybytes.springsecuritybassic.repository.CustomerRepository;


/**
 * Implementacion propio Authentication Provider
 * @author manuonda
 *
 */

@Component
public class EazyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider{

	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username =   authentication.getName();
		String password = authentication.getCredentials().toString(); //password
		List<Customer> customers =  customerRepository.findByEmail(username);
		if ( customers == null || customers.isEmpty()) {
			throw new UsernameNotFoundException("No user registered with this details");
		} else {
			if( passwordEncoder.matches(password, customers.get(0).getPwd())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(username,password,authorities);
			} else {
				throw new BadCredentialsException("Invalid password");
			}
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
