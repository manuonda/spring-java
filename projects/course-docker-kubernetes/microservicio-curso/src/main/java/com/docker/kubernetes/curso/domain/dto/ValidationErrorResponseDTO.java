package com.docker.kubernetes.curso.domain.dto;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrorResponseDTO {
  Map<String, String> errors = new HashMap<>();

  public ValidationErrorResponseDTO(){
      this.errors = new HashMap<>();
  }

  public void addError(String field, String errorMessage){
      this.errors.put(field, errorMessage);
  }
}
