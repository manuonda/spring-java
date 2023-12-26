package com.docker.kubernetes.usuarios.domain.dto;

public record ResponseExceptionDTO(
        String message,
        int status
) {
}
