package com.example.dto;


import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

@Data
@Getter
@Setter
@Builder
public class EmployeeDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
}
