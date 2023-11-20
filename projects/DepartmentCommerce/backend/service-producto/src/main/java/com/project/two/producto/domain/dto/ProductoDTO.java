package com.project.two.producto.domain.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long id;
    @NotNull
    private String name;
    private String description;
    private double price;
    private int qty;

    @NotNull
    private Long idCategoria;


}
