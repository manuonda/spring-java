package com.docker.kubernetes.curso.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    @NotEmpty(message = "No puede ser vacio")
    private String userName;

    private String lastName;

    @NotEmpty(message = "No puede ser vacio")
    private String email;

    private String password;
}
