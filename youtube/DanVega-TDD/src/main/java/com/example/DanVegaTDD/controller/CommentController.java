package com.example.DanVegaTDD.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private static final String BASE_URL="http://localhost:8081/api/v1/comments/";
    private static final String SERVICE_A="serviceA";

    private final RestTemplate restTemplate;

    public CommentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("a")
    @CircuitBreaker(name=SERVICE_A , fallbackMethod = "serviceAFallback")
    public String getServiceA(){
       String baseUrl = BASE_URL + "b";
       return restTemplate.getForObject(baseUrl,
               String.class);
    }

    @GetMapping("b")
    public String getServiceB(){
        return "Response Service b";
    }


    public String serviceAFallback(Exception ex){
        return "This is a fallback method for Service A";
    }




}
