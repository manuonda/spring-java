package org.appmockito.ejemplos.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;

@Entity
@Data
@Table(name="pregunta")
public class Pregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@ManyToOne
	private Examen examen;
	
}
