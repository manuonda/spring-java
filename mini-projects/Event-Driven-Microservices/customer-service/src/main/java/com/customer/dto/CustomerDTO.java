package com.customer.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;

    private String nombre;
    private String apellido;
    private String idType;
    private String idValue;
    private String genero;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String numeroTelefono;
}
