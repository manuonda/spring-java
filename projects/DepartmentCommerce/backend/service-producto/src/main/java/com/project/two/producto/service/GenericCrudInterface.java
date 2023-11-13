package com.project.two.producto.service;

import java.util.List;

public interface GenericCrudInterface<T, ID> {
    T save(T dto);
    T update(T dto, ID id);
    void delete(ID id);
    T findByName(String name);

    T findById(ID id);

    List<T> findAll();
}