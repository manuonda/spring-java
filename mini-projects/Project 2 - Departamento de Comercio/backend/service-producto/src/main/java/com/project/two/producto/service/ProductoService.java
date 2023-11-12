package com.project.two.producto.service;

import com.project.two.producto.domain.Producto;
import com.project.two.producto.dto.ProductoDTO;

import java.util.List;

public interface ProductoService  extends GenericCrudInterface<ProductoDTO, Long>{


    List<ProductoDTO> findAll();

    boolean realizarReposicionProducto(Long idProducto, int cantidadAgregar);


}
