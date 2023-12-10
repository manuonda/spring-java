package com.project.two.files.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class APIResponse {

   private String message;
   private boolean isSuccessful;
   private int statusCode;
   private Object data;

}
