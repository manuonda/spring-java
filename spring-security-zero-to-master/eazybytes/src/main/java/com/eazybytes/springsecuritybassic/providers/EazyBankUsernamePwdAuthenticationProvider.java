package com.eazybytes.springsecuritybassic.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.eazybytes.springsecuritybassic.models.Authority;
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
		if ( customers != null ) {
			System.out.println(customers);
			if ( customers.isEmpty()) {
				throw new UsernameNotFoundException("No user registered with this details");
			} else {
				if( passwordEncoder.matches(password, customers.get(0).getPwd())) {
					return new UsernamePasswordAuthenticationToken(username,password,getGrantedAuthorities(customers.get(0).getAuthorities()));
				} else {
					throw new BadCredentialsException("Invalid password");
				}
			}	
		} else {
			System.out.println("customers null");
			throw new BadCredentialsException("Nto user registered");
		}
		
		
	}

	
	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(Authority authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		
		return grantedAuthorities;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
