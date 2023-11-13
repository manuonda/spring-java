package com.project.two.inventario.service;

import com.project.two.inventario.domain.Inventario;
import com.project.two.inventario.dto.InventarioDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public interface InventarioService  {




    /**
     * Funcion que permite actualizar el inventario
     * del producto relacionado agregando mas cantidades
     * al producto
     * @param idProducto
     * @param cantidadAgregar
     * @return
     */
    Inventario actualizarInventarioReposicion(Long idProducto, int cantidadAgregar);

    /**
     * Funcion que permite actualizar el inventario del producto
     * actualizando la cantidad del producto por las cant.
     * vendidas
     * @param idProducto
     * @param cantVendida
     * @return
     */
    Inventario actualizarInventarioVenta(Long idProducto, int cantVendida );


    List<InventarioDTO> listAll();

   Optional<Inventario> findById(Long id);

}
