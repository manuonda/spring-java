package com.docker.kubernetes.curso.presentation.advice;

public class EntityFound extends RuntimeException{

    public EntityFound(String message)  {
        super(message);
    }
}
