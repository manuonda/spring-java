package org.appmockito.ejemplos.repository;

import java.util.List;

import org.appmockito.ejemplos.models.Banco;

public interface IBancoRepository {
    List<Banco> findAll();

    Banco findById(Long id);

    void update(Banco banco);

}