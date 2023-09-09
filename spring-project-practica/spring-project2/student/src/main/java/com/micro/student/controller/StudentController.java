package com.micro.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.student.dto.StudentDTO;
import com.micro.student.service.StudentServiceImpl;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	@Autowired
	private StudentServiceImpl service;
	
	@PostMapping
	public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO dto) {
	   Optional<StudentDTO> optional = this.service.save(dto);	
	   return ResponseEntity.ok(optional.get());
	}
	
	@GetMapping("/school-id/{id}")
	public ResponseEntity<List<StudentDTO>> getByIdSchool(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getByIdSchool(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<StudentDTO>> all() {
		return ResponseEntity.ok(this.service.all());
	}
	
}
