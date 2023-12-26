package com.docker.kubernetes.usuarios.presentation.advice;

public class EntityNotFound extends RuntimeException{

    public EntityNotFound(String message) {
        super(message);
    }
}
