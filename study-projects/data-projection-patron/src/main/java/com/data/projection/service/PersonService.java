package com.data.projection.service;

import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.projection.dto.PersonDTO;
import com.data.projection.dto.PersonLocationDTO2;
import com.data.projection.projections.PersonFullLocation;
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

	@Override
	public Optional<PersonFullLocation> getPersonFullLocation(Long idPerson) {
		PersonFullLocation person = this.personRepository.getPersonFullLocation(idPerson);
		if ( person != null ) {
			return Optional.of(person);
		}
		return Optional.ofNullable(person);
	}

	@Override
	public PersonDTO getPersonLocationDTO(Long idPerson) {
		Tuple tuple = this.personRepository.getPersonLocationDTO(idPerson);
		PersonDTO dto = new PersonDTO();
		if (tuple != null ) {
			dto.setFirstName(tuple.get(0, String.class));
			dto.setLastName(tuple.get(1, String.class));
			dto.setPhoneNumber(tuple.get(2, String.class));
		}
		return dto;
	}

	@Override
	public PersonLocationDTO2 getPersonLocationDTO2(Long idPerson) {
	    return this.personRepository.getPersonLocationDTO2(idPerson);	
	}

	
	@Override
	public <T> T getPersonLocationDynamically(Long id, Class<PersonLocation> class1) {
		return (T) this.personRepository.getPersonLocationDynamically(id, PersonLocation.class);
	}
	
	

	
}
