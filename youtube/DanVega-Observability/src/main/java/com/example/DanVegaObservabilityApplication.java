package com.example;

import com.example.post.JsonPlaceHolderService;
import com.example.post.Post;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class DanVegaObservabilityApplication {

	private static final Logger logger = LoggerFactory.getLogger(DanVegaObservabilityApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DanVegaObservabilityApplication.class, args);
	}



	@Bean
	JsonPlaceHolderService jsonPlaceHolder(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
	    return factory.createClient(JsonPlaceHolderService.class);
	}

	@Bean
	@Observed(name="posts.load-all-posts", contextualName = "post-service.find-all" )
	CommandLineRunner commandLineRunner(JsonPlaceHolderService jsonPlaceHolderService) {
		//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator.observability
		return args -> {
			/*
			  Observation.createNotStarted("posts.load-all-posts", observationRegistry)
					.lowCardinalityKeyValue("author","manuonda")
					.contextualName("post-service.find-all")
					.observe(jsonPlaceHolderService::findAll);*/
			/*List<Post> posts = jsonPlaceHolderService.findAll();
			logger.info("Posts : {}", posts);*/
			jsonPlaceHolderService.findAll();
		};
	}
}
