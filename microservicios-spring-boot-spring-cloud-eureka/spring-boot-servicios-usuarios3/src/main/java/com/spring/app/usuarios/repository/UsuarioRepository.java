package com.spring.app.usuarios.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.spring.app.commons.usuarios.entity.Usuario;



/**
 * <b>Class Usuario Repository </b>
 * @author dgarcia
 * 
 *
 */

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository  extends PagingAndSortingRepository<Usuario, Long>{

	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("nombre") String username);
	
	@Query( value = "select u from Usuario u where u.username = ?1")
	public Usuario obternPorUsername(String username);
	
	
}
