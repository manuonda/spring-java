package com.project1.students.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project1.students.models.Student;
import com.project1.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl {
   
	private final StudentRepository repository;
	
	
	public Student saveStudent(Student student) {
		return this.repository.save(student);
	}
	
	public List<Student> findAdllStudents(){
		return repository.findAll();
	}
	
	
	public List<Student> findBySchoolId(Long idSchool) {
		return this.repository.findAllBySchoolId(idSchool);
		
	}
	
}
