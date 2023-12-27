package com.docker.kubernetes.curso.repository;

import com.docker.kubernetes.curso.domain.entity.CursoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Long> {


}
