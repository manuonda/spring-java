package com.micro.student.model;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
  
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private Long id;
  
  @Nonnull
  private String firstName;
  
  @NotNull
  private String lastName;
  
  @Email
  private String email;
  
  
  @Column(name="id_school")
  private Long idSchool;
}
  