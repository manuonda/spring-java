package com.cours1.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.cours1.entity.Student;


public interface StudentDAO {

	Collection<Student> all();
	Student findById(Integer id);
	void removeStudentById(Integer id);
	Student update(Student student);
}
