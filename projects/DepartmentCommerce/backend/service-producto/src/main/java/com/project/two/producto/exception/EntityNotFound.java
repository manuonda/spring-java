package com.project.two.producto.exception;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


public class EntityNotFound extends RuntimeException{

     public  EntityNotFound(String message) {
         super(message);
     }

}
