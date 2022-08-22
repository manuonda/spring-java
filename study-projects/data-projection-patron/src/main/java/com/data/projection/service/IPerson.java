package com.data.projection.service;

import java.util.Optional;

import com.data.projection.dto.PersonDTO;
import com.data.projection.dto.PersonLocationDTO2;
import com.data.projection.projections.PersonFullLocation;
import com.data.projection.projections.PersonLocation;

public interface IPerson {

	Optional<PersonLocation> getCloseProjection(Long idPerson);
	
	Optional<PersonFullLocation> getPersonFullLocation(Long idPerson);
	
	PersonDTO getPersonLocationDTO(Long idPerson);
	
	PersonLocationDTO2 getPersonLocationDTO2(Long idPerson);
	<T> T getPersonLocationDynamically(Long id, Class<PersonLocation> class1);
	
}
