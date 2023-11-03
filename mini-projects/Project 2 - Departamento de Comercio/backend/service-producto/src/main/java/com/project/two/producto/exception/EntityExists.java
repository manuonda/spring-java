package com.project.two.producto.exception;

public class EntityExists extends RuntimeException{

    public EntityExists(String message) {
        super(message);
    }
}
