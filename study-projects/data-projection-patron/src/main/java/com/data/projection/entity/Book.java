package com.data.projection.entity;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime eta;
	private boolean finished;
	private TaskStatus taskStatus;
	
	
}
