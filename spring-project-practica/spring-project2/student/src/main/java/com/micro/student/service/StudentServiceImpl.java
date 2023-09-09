package com.micro.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.micro.student.dto.StudentDTO;
import com.micro.student.model.Student;
import com.micro.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository repository;
	
	StudentServiceImpl(StudentRepository repository) {
	  this.repository = repository;	
	}

	@Override
	public Optional<StudentDTO> update(StudentDTO dto) {
	  return null;
	}

	@Override
	public Optional<StudentDTO> save(StudentDTO dto) {
	   StudentDTO saveDTO =  new StudentDTO();
		try {
			Student entity = Student.builder()
					.id(dto.getId())
					.firstName(dto.getFirstName())
					.lastName(dto.getLastName())
					.email(dto.getEmail())
					.idSchool(dto.getIdSchool())
					.build();
			entity = this.repository.save(entity);
            saveDTO = new StudentDTO();
            saveDTO.setId(entity.getId());
            saveDTO.setFirstName(entity.getFirstName());
            saveDTO.setLastName(entity.getLastName());
            saveDTO.setEmail(entity.getEmail());
            saveDTO.setIdSchool(entity.getIdSchool());
            

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return Optional.of(null);
		}
		
		return Optional.of(saveDTO);
		
	}

	@Override
	public List<StudentDTO> all() {
		List<Student> list = this.repository.findAll();
		List<StudentDTO> listDTO = new ArrayList<>();
		if (list != null) {
			listDTO = list.stream()
					.map( s -> new StudentDTO(s))
					.collect(Collectors.toList());
		}
		return listDTO;
	}

	@Override
	public List<StudentDTO> getByIdSchool(Long idSchool) {
		List<Student> list = this.repository.findByIdSchool(idSchool);
		List<StudentDTO> listDTO = new ArrayList<>();
		if (list != null) {
			listDTO = list.stream()
					.map( s -> new StudentDTO(s))
					.collect(Collectors.toList());
		}
		return listDTO;
		
	}
	
	
}
