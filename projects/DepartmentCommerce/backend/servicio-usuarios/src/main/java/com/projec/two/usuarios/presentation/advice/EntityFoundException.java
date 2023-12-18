package com.projec.two.usuarios.presentation.advice;

public class EntityFoundException extends RuntimeException{
    public EntityFoundException(String message) {
        super(message);
    }
}
