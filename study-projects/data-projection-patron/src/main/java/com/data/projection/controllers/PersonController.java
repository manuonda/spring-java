package com.data.projection.controllers;

import java.awt.PageAttributes.MediaType;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.projection.dto.PersonDTO;
import com.data.projection.dto.PersonLocationDTO2;
import com.data.projection.projections.PersonFullLocation;
import com.data.projection.projections.PersonLocation;
import com.data.projection.service.IPerson;
import com.data.projection.service.PersonService;

import lombok.Getter;

@RestController	
public class PersonController {

    @Autowired
    IPerson iperson;
    
    @GetMapping(value = "/person")
	public String getMethodName() {
		return "hola mundo";
	}

   
	@GetMapping(value = "/person/closeProject" ,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonLocation> getPersonLocation(@RequestParam("id") Long id) {
		Optional<PersonLocation> person = this.iperson.getCloseProjection(id);
		return ResponseEntity.ok().body(person.isPresent() ? person.get(): null);
	}

	
	@GetMapping(value = "/person/fullLocation" ,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonFullLocation> getOpenProject(@RequestParam("id") Long id) {
		Optional<PersonFullLocation> person = this.iperson.getPersonFullLocation(id);
		return ResponseEntity.ok().body(person.isPresent() ? person.get(): null);
	}

	@GetMapping(value = "/person/tuple" ,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> getTuple(@RequestParam("id") Long id) {
		PersonDTO person = this.iperson.getPersonLocationDTO(id);
		return ResponseEntity.ok().body(person);
	}
	
	@GetMapping(value = "/person/class_based_named_query" ,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonLocationDTO2> getClassBasedNamedQuery(@RequestParam("id") Long id) {
		PersonLocationDTO2 person = this.iperson.getPersonLocationDTO2(id);
		return ResponseEntity.ok().body(person);
	}
	
	@GetMapping(value = "/person/dynamically" ,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getClassBasedDinamically(@RequestParam("id") Long id) {
		Object person = this.iperson.getPersonLocationDynamically(id,PersonLocation.class);
		return ResponseEntity.ok().body(person);
	}
}
