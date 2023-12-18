package com.projec.two.usuarios.domain.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDTO {

     private Long id;
     @NotNull( message =  "El nombre de usuario no puede ser nulo")
     private String userName;
     private String lastName;
     @NotNull(message = "El email no puede ser nulo")
     private String email;
}
