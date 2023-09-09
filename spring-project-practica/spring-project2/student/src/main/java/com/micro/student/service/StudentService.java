package com.micro.student.service;

import java.util.List;
import java.util.Optional;

import com.micro.student.dto.StudentDTO;

public interface StudentService {
   
	Optional<StudentDTO> update(StudentDTO entity);
	
	Optional<StudentDTO> save(StudentDTO entity);
	
	List<StudentDTO> all();
	
	List<StudentDTO> getByIdSchool(Long idSchool);
	
}
