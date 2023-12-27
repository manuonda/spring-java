package com.docker.kubernetes.curso.domain.entity;


import com.docker.kubernetes.curso.domain.models.Usuario;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "tbl_curso_usuario")
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
