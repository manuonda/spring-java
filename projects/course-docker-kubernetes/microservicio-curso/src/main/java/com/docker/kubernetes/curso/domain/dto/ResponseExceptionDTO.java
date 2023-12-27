package com.docker.kubernetes.curso.domain.dto;

public record ResponseExceptionDTO(
        int status,
        String message
) {

}
