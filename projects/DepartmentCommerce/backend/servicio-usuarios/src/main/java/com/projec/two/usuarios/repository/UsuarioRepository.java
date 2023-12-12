package com.projec.two.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projec.two.usuarios.domain.entity.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
