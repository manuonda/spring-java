package com.docker.kubernetes.curso.domain.dto;


import lombok.Data;

@Data
public class CursoUsuarioDTO {

    private Long idCurso;
    private String nombreCurso;
    private Long idUsuario;
    private String nombreUsuario;
}
