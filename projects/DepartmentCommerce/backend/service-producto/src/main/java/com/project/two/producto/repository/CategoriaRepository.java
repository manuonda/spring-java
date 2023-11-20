package com.project.two.producto.repository;

import com.project.two.producto.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAll();
    Optional<Categoria> findByName(String name);
}
