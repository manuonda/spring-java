package org.appmockito.ejemplos.repository;

import java.util.List;

import org.appmockito.ejemplos.models.Cuenta;

public interface ICuentaRepository {
    List<Cuenta> findAll();

    Cuenta findById(Long id);

    void update(Cuenta cuenta);
}