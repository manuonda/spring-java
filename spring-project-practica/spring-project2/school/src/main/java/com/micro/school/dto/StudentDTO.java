package com.micro.school.dto;

import org.springframework.data.annotation.Id;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
  
  @NotNull
  private Long id;
  
  @Nonnull
  private String firstName;
  
  @NotNull
  private String lastName;
  
  @Email
  private String email;
  
  private Long idSchool;
  
  
}