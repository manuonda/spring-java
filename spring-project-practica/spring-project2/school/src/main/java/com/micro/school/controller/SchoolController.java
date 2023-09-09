package com.micro.school.controller;

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

import com.micro.school.dto.SchoolDTO;
import com.micro.school.service.SchoolServiceImpl;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {

	@Autowired
	private SchoolServiceImpl service;
	
	@PostMapping
	public ResponseEntity<SchoolDTO> save(@RequestBody SchoolDTO dto) {
	   Optional<SchoolDTO> optional = this.service.save(dto);	
	   return ResponseEntity.ok(optional.get());
	}
	
	@RequestMapping("/all")
	public ResponseEntity<List<SchoolDTO>> findAll() {
		return ResponseEntity.ok(this.service.all());
	}
	
	
	@GetMapping("/school-id/{idSchool}")
	public ResponseEntity<SchoolDTO> getByIdSchool(@PathVariable Long idSchool) {
		return ResponseEntity.ok(this.service.findByIdSchool(idSchool));
	}
}
