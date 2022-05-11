package com.example.demoh2.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value="add")
	public Task add(@RequestBody Task task) {
	  Task newTask =  this.iTaskService.insert(task);
	  return newTask;
	}
	
	@DeleteMapping(value="delete/{id}")
	public void  delete(@PathVariable("id") Long id) {
	   this.iTaskService.delete(id);
	}
	
	@PutMapping(value="update/{id}")
	public Task update(Task task, @PathVariable("id") Long id ){
		return this.iTaskService.update(task, id);
	}
	
	

}
