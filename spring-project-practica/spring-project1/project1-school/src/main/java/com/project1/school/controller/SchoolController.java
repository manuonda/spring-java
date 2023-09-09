package com.project1.school.controller;

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

import com.project1.school.dto.SchoolDTO;
import com.project1.school.models.School;
import com.project1.school.service.SchoolServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
  
	@Autowired
	private SchoolServiceImpl service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public School save(@RequestBody School school) {
		return this.service.saveStudent(school);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<School>> getListAll(){
		return ResponseEntity.status(HttpStatus.OK).body(this.service.findAdllStudents());
	}
	
	@GetMapping("/with-students/{school-id}")
	public SchoolDTO findAllSchools(
			@PathVariable("school-id") Long idSchool){
		return this.service.findAllSchools(idSchool);
	}
	
}
