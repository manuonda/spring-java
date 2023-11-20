package com.project.two.inventario.domain.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InventarioDTO {

    private Long id;
    private Long idProducto;
    private int qty;
}
