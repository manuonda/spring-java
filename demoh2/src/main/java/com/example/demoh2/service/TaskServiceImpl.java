package com.example.demoh2.service;

import java.util.List;
import java.util.Optional;

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
	public void delete(Long id) {
	   this.dao.deleteById(id);
   }

	@Override
	public Task update(Task task,Long id ) {
		Optional<Task> taskFind = this.dao.findById(id);
		if (taskFind != null ) {
			task = this.dao.save(task);	
			return task;
			
		}
		return null;
	}
   
}
