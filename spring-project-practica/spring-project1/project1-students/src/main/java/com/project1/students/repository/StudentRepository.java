package com.project1.students.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.students.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
  List<Student> findAllBySchoolId(Long id);
}
