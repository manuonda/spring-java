package com.project.two.producto.common.exception;

public class EntityExists extends RuntimeException{

    public EntityExists(String message) {
        super(message);
    }
}
