package com.spring.app.security.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.app.security.example.model.Contacto;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
