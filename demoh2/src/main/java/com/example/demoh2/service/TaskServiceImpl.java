package com.example.demoh2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoh2.dao.ITaskDAO;
import com.example.demoh2.entity.Task;

@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	private ITaskDAO dao;
	
	@Override
	public List<Task> getAll() {
		return (List<Task>) this.dao.findAll();
	}

	@Override
	public Task insert(Task task) {
       return this.dao.save(task);
		
	}

	@Override
	public void delete(Task task) {
		this.dao.delete(task);
	}

	@Override
	public Task update(Task task) {
		return this.dao.save(task);
	}
   
}
