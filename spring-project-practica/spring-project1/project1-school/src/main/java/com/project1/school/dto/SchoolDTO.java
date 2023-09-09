package com.project1.school.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolDTO {

	  private String name;
	  private String email;
	  private List<StudentDTO> students;
	
}
