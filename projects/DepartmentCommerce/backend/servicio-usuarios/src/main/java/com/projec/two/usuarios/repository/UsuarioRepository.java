package com.projec.two.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projec.two.usuarios.domain.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByUserName(String userName);

    Optional<Usuario> findByEmail(String email);

}
