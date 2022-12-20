package com.spring.app.security.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.app.security.example.model.Usuario;
import com.spring.app.security.example.repository.UsuarioRepository;

@Service
public class UserDetailServiceImpl  implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Usuario usuario =  usuarioRepository.findOneByEmail(username)
				 .orElseThrow(()-> new UsernameNotFoundException("El usuario con email "+ username + " no existe"));
	   
		 // Realiza una conversion de usuario obtenido 
		 // de database y devuelv un usuario UserDetails
		 return new UserDetailsImpl(usuario);
	
	}

}
