package com.project.two.inventario.business.service;

import com.project.two.inventario.domain.entity.Inventario;
import com.project.two.inventario.domain.dto.InventarioDTO;

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

   Optional<InventarioDTO> findById(Long id);

    /**
     * Save Inventario DTO
     * @param dto
     * @return
     */
   InventarioDTO save(InventarioDTO dto);

}
