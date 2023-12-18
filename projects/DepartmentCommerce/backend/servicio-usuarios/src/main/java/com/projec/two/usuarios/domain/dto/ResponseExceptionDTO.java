package com.projec.two.usuarios.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseExceptionDTO {
  private String message;
  private int status;
}
