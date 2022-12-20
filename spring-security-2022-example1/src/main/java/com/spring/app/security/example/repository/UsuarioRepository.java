package com.spring.app.security.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.app.security.example.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	/**
	 * Find by Email
	 * @param email
	 * @return
	 */
 	Optional<Usuario> findOneByEmail(String email);
}
