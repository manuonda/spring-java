package com.project.two.inventario.exception;

public class EntityNotFound extends RuntimeException{
    public EntityNotFound(String message){
        super(message);
    }
}
