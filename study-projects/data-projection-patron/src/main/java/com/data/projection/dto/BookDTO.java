package com.data.projection.dto;

import java.time.LocalDateTime;

import com.data.projection.entity.TaskStatus;

import lombok.Data;

@Data
public class BookDTO {

	private Long id;
	private String name;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime eta;
	private boolean finished;
	private TaskStatus taskStatus;
	
	
}
