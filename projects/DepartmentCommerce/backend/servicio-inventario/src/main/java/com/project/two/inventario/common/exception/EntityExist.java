package com.project.two.inventario.common.exception;

public class EntityExist extends RuntimeException{

    public EntityExist(String message) {
        super(message);
    }
}
