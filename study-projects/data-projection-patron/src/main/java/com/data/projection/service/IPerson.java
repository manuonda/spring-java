package com.data.projection.service;

import java.util.Optional;

import com.data.projection.projections.PersonLocation;

public interface IPerson {

	Optional<PersonLocation> getCloseProjection(Long idPerson);
}
