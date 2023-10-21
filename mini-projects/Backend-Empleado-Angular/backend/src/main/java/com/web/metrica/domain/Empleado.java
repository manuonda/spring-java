package com.web.metrica.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.repository.NoRepositoryBean;

@Data
@Builder
@Entity(name = "empleado")
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nombre;

    String apellido;

    String email;


}
