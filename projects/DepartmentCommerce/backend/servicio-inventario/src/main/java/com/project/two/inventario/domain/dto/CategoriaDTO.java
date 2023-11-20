package com.project.two.inventario.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Long id;

    @NotNull(message = "El nombre de categoria no puede estar vacio")
    private String name;

    private String description;
}
