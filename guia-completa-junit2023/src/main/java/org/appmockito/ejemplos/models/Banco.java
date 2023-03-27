package org.appmockito.ejemplos.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="banco")
@Builder
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Banco {
    private Long id;
    private String nombre;
    private int totalTransferencias;

}
