package com.project.two.commons.dto;


/**
 * Evento relazionado a Inventario
 * @param event
 * @param idProducto
 * @param cantidad
 */
public record EventoInventarioDTO(
        String event,
        Long idProducto,
        int cantidad
) {

}
