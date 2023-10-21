package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.*;


@Data
@Getter
@Setter
@Builder
@Entity(name="employee")
public class Employee {

    public Employee(){}

    public Employee(Integer id, boolean working, String nombre, String email, String apellido) {
        this.id = id;
        this.working = working;
        this.nombre = nombre;
        this.email = email;
        this.apellido = apellido;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean working;

    private String nombre;

    @Email(message = "Email should be valid")
    private String email;

    private String apellido;


}
