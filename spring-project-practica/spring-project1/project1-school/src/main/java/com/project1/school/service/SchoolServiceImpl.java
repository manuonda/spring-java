package com.project1.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.school.StudentClient;
import com.project1.school.WebFluxConfig;
import com.project1.school.dto.SchoolDTO;
import com.project1.school.dto.StudentDTO;
import com.project1.school.models.School;
import com.project1.school.repository.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl {
   
	private final SchoolRepository repository;
	
	WebFluxConfig webFluxConfig;
	
	@Autowired
	private StudentClient studentClient;
	
	public School saveStudent(School student) {
		return this.repository.save(student);
	}
	
	public List<School> findAdllStudents(){
		return repository.findAll();
	}
	
	public SchoolDTO  findAllSchools(Long idSchool) {
		var school = repository.findById(idSchool)
				.orElse( 
						School.builder()
						.name("NOT_FOUND")
						.email("NOT_FOUND")
						.build());
        var students = studentClient.foundAllStudentsByIdSchool(idSchool);
        return SchoolDTO.builder()
        		.name(school.getName())
        		.email(school.getEmail())
        		.students(students)
        		.build();
	}
	
}
