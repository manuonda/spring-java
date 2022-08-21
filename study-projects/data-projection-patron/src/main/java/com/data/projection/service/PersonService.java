package com.data.projection.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.projection.projections.PersonLocation;
import com.data.projection.repository.PersonRepository;

@Service
public class PersonService implements IPerson {

	private PersonRepository personRepository;
	
	PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Override
	public Optional<PersonLocation> getCloseProjection(Long idPerson) {
		PersonLocation person = this.personRepository.getPersonLocation(idPerson);
		if ( person != null ) {
			return Optional.of(person);
		}
		return Optional.ofNullable(person);
	}

	
}
