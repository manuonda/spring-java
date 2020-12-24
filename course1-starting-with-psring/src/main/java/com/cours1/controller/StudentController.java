package com.cours1.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.cours1.entity.Student;
import com.cours1.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Student> geAll(){
		return this.studentService.getAllStudents();
	}
	
	@RequestMapping(value="/{id}"  , method = RequestMethod.GET)
	public Student findById(@PathVariable("id")Integer id) {
		return this.studentService.findById(id);
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.DELETE)
	public void removeById(@PathVariable("id") Integer id) {
		this.studentService.remove(id);
	}
	
	@RequestMapping(value="/{id}" , method = RequestMethod.PUT)
	public Student update(@PathVariable("id") Integer id,  Student update) {
		return this.studentService.update(update);
	}
	
	
	
}
