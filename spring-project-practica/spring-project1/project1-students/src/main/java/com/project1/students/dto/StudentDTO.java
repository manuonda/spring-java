package com.project1.students.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Integer schoolId;
	
}
