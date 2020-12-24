package com.example.demoh2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoh2.entity.Task;
import com.example.demoh2.service.ITaskService;

@RestController
@RequestMapping(value = "/tasks/")
public class TaskController {
	
	@Autowired
	private ITaskService iTaskService;
	
	
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> getAll(){
		return this.iTaskService.getAll();
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public Task add(Task task) {
	  Task newTask =  this.iTaskService.insert(task);
	  return newTask;
	}
	
	

}
