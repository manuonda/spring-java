package com.cours1.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cours1.entity.Student;

@Repository
@Qualifier("studentFake")
public class StudentFakeDAOImpl implements StudentDAO, Serializable {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = -1841792556175656585L;
	private static Map<Integer, Student> students;
	
	static {
		students = new HashMap<Integer, Student>(){
			{
				put(1, new Student(1,"David","Garcia", 23));
				put(2, new Student(2,"Andres","Garcia", 23));
				put(3, new Student(3,"Raquel","Garcia", 23));
				put(4, new Student(4,"Sofia","Garcia", 23));
				
			}
		};
	}
	
	public Collection<Student> all() {
		return this.students.values();
	}

	public Student findById(Integer id) {
		return this.students.get(id);
	}
	
	public void removeStudentById(Integer id) {
		this.students.remove(id);
	}

	@Override
	public Student update(Student student) {
		Student s = this.students.get(student.getId());
		s.setAge(student.getAge());
		s.setUserName(student.getUserName());
		s.setName(student.getName());
		this.students.put(s.getId(),student);
		return s;
	}

}
