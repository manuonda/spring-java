package com.webclient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;


import reactor.core.publisher.Mono;

@Service
public class ResponseDragonBallService {

	// tutorial 1 - https://www.techgeeknext.com/spring-boot/webflux/spring-boot-webclient-example
	// tutorial 2 : https://www.cleveritgroup.com/blog/programacion-reactiva-con-spring-webflux-parte-2
	// tutorial 3 : https://javatechonline.com/webclient-in-spring-boot/
	
	@Autowired
	private WebClient webClient;
	
	
	private static final Logger log = LoggerFactory.getLogger(ResponseDragonBallService.class);

	
	public List<String> getDragonBallData(){
	   try {
    
			List<String> listDataDragonBall = this.webClient.get()
		   .uri("/api/v1/dragonball/characters")
		   .retrieve()
		   .bodyToFlux(String.class)
		   .collect(Collectors.toList())
		   .share()
		   .block();
			return listDataDragonBall;
	  }catch(WebClientResponseException wcre) {
		 log.error("Error Response Code is {}  and Response Body is {}",
				 wcre.getRawStatusCode(), wcre.getResponseBodyAsString());  
		 throw wcre;
	  }catch (Exception ex) {
	
		 log.error("Exception in method retrieveAllInvoices()",ex);
         throw ex;
	 }
	}
}
