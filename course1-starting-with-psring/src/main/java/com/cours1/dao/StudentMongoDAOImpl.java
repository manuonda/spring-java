package com.cours1.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cours1.entity.Student;


@Repository
@Qualifier("studentMongo")
public class StudentMongoDAOImpl implements StudentDAO {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = -1841792556175656585L;
	private static Map<Integer, Student> students;
	
	static {
		students = new HashMap<Integer, Student>(){
			{
				put(1, new Student(1,"DavidMongo1","Garcia", 23));
				put(2, new Student(2,"AndresMongo","Garcia", 23));
				put(3, new Student(3,"RaquelMongo","Garcia", 23));
				put(4, new Student(4,"SofiaMongo","Garcia", 23));
				
			}
		};
	}
	
	public Collection<Student> all() {
		return this.students.values();
	}
	@Override
	public Student findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeStudentById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Student update(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

}
