package com.project1.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project1.students.models.Student;
import com.project1.students.service.StudentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
  
	@Autowired
	private StudentServiceImpl service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Student save(@RequestBody Student student) {
		return this.service.saveStudent(student);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Student>> getListAll(){
		return ResponseEntity.status(HttpStatus.OK).body(this.service.findAdllStudents());
	}
	
	
	@GetMapping("/school/{school-id}")
	public ResponseEntity<List<Student>> findBySchoolId(@PathVariable("school-id") Long id) {
		return ResponseEntity.ok(this.service.findBySchoolId(id));
	}
}
