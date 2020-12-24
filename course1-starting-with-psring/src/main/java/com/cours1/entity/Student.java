package com.cours1.entity;


public class Student {

	private Integer id;
	private String name;
	private String userName;
	private Integer age;
	
	
	
	public Student(Integer id, String name, String userName, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
