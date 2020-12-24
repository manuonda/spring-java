package com.cours1.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cours1.dao.StudentDAO;
import com.cours1.entity.Student;

@Service
public class StudentService {

	@Autowired
	@Qualifier("studentFake")
	private StudentDAO studentDAO;
	
	
	public Collection<Student> getAllStudents(){
		return this.studentDAO.all();
	}
	
	public Student findById(Integer id) {
		return this.studentDAO.findById(id);
	}
	
	public void remove(Integer id) {
		this.studentDAO.removeStudentById(id);
	}
	
	public Student update(Student student) {
		return this.studentDAO.update(student);
	}
	
	
}
