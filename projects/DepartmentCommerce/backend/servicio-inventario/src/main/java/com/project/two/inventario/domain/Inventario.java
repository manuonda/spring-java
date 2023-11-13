package com.project.two.inventario.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tbl_inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private int qty;
}
