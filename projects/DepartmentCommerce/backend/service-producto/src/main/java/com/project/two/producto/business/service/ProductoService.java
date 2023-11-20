package com.project.two.producto.business.service;

import com.project.two.producto.domain.dto.ProductoDTO;

import java.util.List;

public interface ProductoService  extends GenericCrudInterface<ProductoDTO, Long>{


    List<ProductoDTO> findAll();

    boolean realizarReposicionProducto(Long idProducto, int cantidadAgregar);


}
