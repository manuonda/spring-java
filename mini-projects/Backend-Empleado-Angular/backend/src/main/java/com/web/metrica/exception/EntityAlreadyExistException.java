package com.web.metrica.exception;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException(String message) {
        super(message);
        System.out.println("entity already exist exception : " +message);

    }


}
