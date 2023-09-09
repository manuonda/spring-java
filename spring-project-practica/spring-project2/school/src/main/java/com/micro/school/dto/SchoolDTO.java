package com.micro.school.dto;


import java.util.List;

import com.micro.school.model.School;

import jakarta.annotation.Nonnull;
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
public class SchoolDTO {
  
  @NotNull
  private Long id;
  
  @Nonnull
  private String name;
  
  @Email
  private String email;

  List<StudentDTO> students;
  
  public SchoolDTO(School entity) {
	  this.id = entity.getId();
	  this.name = entity.getName();
	  this.email = entity.getEmail();
	  
  }
}