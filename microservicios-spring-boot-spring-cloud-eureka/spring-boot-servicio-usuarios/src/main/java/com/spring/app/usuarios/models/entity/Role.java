package com.spring.app.usuarios.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="roles")
@Data
public class Role implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1209328102566998165L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	

}
