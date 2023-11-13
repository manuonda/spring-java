package com.web.metrica.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}
