package com.docker.kubernetes.usuarios.domain.dto;


import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String username;
    private String lastname;
    private String email;
}
