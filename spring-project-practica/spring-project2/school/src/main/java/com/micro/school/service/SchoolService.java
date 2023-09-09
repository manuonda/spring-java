package com.micro.school.service;

import java.util.List;
import java.util.Optional;

import com.micro.school.dto.SchoolDTO;

public interface SchoolService {
   
	Optional<SchoolDTO> update(SchoolDTO entity);
	
	Optional<SchoolDTO> save(SchoolDTO entity);
	
	List<SchoolDTO> all();
	
	SchoolDTO findByIdSchool(Long id);
	
}
