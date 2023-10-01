package com.example.dto;


import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

import java.util.function.IntToDoubleFunction;


@Builder
public record EmployeeDTO(Integer id, String nombre, String apellido, String email) { }
