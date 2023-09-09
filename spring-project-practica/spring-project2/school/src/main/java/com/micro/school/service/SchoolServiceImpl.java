package com.micro.school.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.school.client.StudentClient;
import com.micro.school.dto.SchoolDTO;
import com.micro.school.model.School;
import com.micro.school.repository.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository repository;
	
	@Autowired
	private StudentClient studentClient;
	
	SchoolServiceImpl(SchoolRepository repository) {
	  this.repository = repository;	
	}

	@Override
	public Optional<SchoolDTO> update(SchoolDTO dto) {
	  return null;
	}

	@Override
	public Optional<SchoolDTO> save(SchoolDTO dto) {
	   SchoolDTO saveDTO =  new SchoolDTO();
		try {
			School entity = School.builder()
					.id(dto.getId())
					.name(dto.getName())
					.email(dto.getEmail())
					.build();
			entity = this.repository.save(entity);
            saveDTO = new SchoolDTO();
            saveDTO.setId(entity.getId());
            saveDTO.setName(entity.getName());
            saveDTO.setEmail(entity.getEmail());
            

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return Optional.of(null);
		}
		
		return Optional.of(saveDTO);
		
	}

	@Override
	public List<SchoolDTO> all() {
		List<SchoolDTO> listDTO = new ArrayList<>();
		List<School> list = new ArrayList<>();
		list = this.repository.findAll();
		if (list != null) {
			listDTO = list.stream().map( entity -> new SchoolDTO(entity))
					.collect(Collectors.toList());
		}
		
		return listDTO;
	}

	@Override
	public SchoolDTO findByIdSchool(Long id) {
		var school = this.repository.findById(id)
				.orElse(
					School.builder()
					.email("NOT_FOUND")
					.name("NOT_FOUND")
					.build());
		SchoolDTO schoolDTO = new SchoolDTO();
		
		var students = studentClient.findByIdSchool(id);
		schoolDTO.setName(school.getName());
		schoolDTO.setEmail(school.getEmail());
	    schoolDTO.setStudents(students);
		return schoolDTO;
	}
	
	
}
