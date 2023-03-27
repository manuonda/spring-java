package org.appmockito.ejemplos.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cuenta")
@Builder
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cuenta {
    private Long id;
    private String persona;
    private BigDecimal saldo;

}
