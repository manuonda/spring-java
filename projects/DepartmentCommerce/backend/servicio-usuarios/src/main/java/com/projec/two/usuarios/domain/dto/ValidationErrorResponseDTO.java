package com.projec.two.usuarios.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class    ValidationErrorResponseDTO {

    private Map<String, String> errors;

    public ValidationErrorResponseDTO(){
        this.errors = new HashMap<>();
    }

    public void addError(String fieldName, String errorMessage){
        this.errors.put(fieldName, errorMessage);
    }


}
