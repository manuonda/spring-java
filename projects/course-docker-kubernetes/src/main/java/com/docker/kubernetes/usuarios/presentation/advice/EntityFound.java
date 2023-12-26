package com.docker.kubernetes.usuarios.presentation.advice;

public class EntityFound extends RuntimeException {
    public EntityFound(String message) {
        super(message);
    }
}
