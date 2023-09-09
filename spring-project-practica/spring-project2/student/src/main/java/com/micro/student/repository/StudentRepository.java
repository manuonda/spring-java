package com.micro.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.student.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
 
	List<Student> findByIdSchool(Long idSchool);
}
