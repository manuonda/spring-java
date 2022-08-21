package com.data.projection.controllers;

import java.awt.PageAttributes.MediaType;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
