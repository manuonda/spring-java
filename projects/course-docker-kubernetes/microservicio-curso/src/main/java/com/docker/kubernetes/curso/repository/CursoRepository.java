package com.docker.kubernetes.curso.repository;

import com.docker.kubernetes.curso.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByName(String nombre);
}
