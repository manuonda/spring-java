package com.project.two.inventario.exception;

public class EntityExist extends RuntimeException{

    public EntityExist(String message) {
        super(message);
    }
}
