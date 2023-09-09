package com.restclient;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PostService {

	private RestClient restClient;
	
	public PostService(){
		this.restClient = RestClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
	}
	
	public List<Post> findAll() {
		return restClient.get()
				.uri("/posts")
				.retrieve()
				.body(new ParameterizedTypeReference<List<Post>>() {});
	}

	public Post getById(Integer id) {
		return restClient.get()
		.uri("/posts/{id}",id)
		.retrieve()
		.body(Post.class);
	}


	public Post create(Post post) {
		return restClient.post()
		.uri("/posts",post)
		.contentType(MediaType.APPLICATION_JSON)
		.body(post)
		.retrieve()
		.body(Post.class);
	}


	public Post updatePost(Post post, Integer id) {
		  return restClient.put()
		  .uri("/posts/{id}",id)
		  .contentType(MediaType.APPLICATION_JSON) 
		  .body(post)
		  .retrieve()
		  .body(Post.class);
	}
	public void delete(Integer id) {
		 restClient.delete()
		.uri("/posts/{id}", id)
		.retrieve()
		.toBodilessEntity()	;
	}
}
