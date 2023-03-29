package org.appmockito.ejemplos.repository;

import java.util.List;

import org.appmockito.ejemplos.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBancoRepository extends JpaRepository<Banco,Long> {
    List<Banco> findAll();



	Banco getById(long l);

}