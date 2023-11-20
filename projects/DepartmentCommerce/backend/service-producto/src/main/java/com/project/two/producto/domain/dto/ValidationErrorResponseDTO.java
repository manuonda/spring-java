package com.project.two.producto.domain.dto;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
public class ValidationErrorResponseDTO {

    private Map<String, String> errors;
    public ValidationErrorResponseDTO(){
        errors = new HashMap<>();
    }

    public void addError(String fieldName, String errorMessage) {
        errors.put(fieldName, errorMessage);
    }
}
