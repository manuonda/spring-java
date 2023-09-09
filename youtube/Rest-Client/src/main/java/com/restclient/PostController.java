package com.restclient;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService service;
	
	@GetMapping("/list")
    List<Post> findAll() {
		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public Post hola(@PathVariable("id") Integer id){
		return this.service.getById(id);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	Post create(@RequestBody Post post) {
       return this.service.create(post);   
	}

	@PutMapping("/{id}")
	Post update(@RequestBody Post post , @PathVariable("id") Integer id) {
		return this.service.updatePost(post, id);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@RequestParam("id") Integer id ) {
	   this.service.delete(id);
	} 


}
