package com.project.two.producto.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoriaDTO {

    private Long id;

    @NotNull
    private String name;
    private String description;
}
