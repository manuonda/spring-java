package org.appmockito.ejemplos.repository;

import java.util.List;
import java.util.Optional;

import org.appmockito.ejemplos.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findAll();

    Optional<Cuenta> findById(Long id);
    Cuenta getById(Long id);

}