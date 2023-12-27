package com.docker.kubernetes.usuarios.domain.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter

public class ValidationErrorResponseDTO {
   private Map<String,String> errors = new HashMap<>();

   public ValidationErrorResponseDTO(){
       this.errors = new HashMap<>();
   }

   public void addError(String fieldName, String errorMessage) {
       this.errors.put(fieldName,errorMessage);
   }
}
