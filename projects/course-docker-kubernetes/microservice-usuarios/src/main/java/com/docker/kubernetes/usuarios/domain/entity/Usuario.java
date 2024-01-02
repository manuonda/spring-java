    package com.docker.kubernetes.usuarios.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tbl_usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="last_name")
    private String lastName;

    @Column(unique = true)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name="usuario_alta")
    private String usuarioAlta;

    @Column(name="usuario_modificacion")
    private String usuarioModificacion;

    @Column(name="fecha_alta")
    private LocalDateTime fechaAlta;

    @Column(name="fecha_modificacion")
    private LocalDateTime fechaModifiacion;


}
