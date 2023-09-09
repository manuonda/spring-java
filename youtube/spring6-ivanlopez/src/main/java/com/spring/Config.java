package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class Config {
    
    /**
	 * Bean corresponiente 
	 * para la configuration de comunicacion con el servicios
	 * @param builder
	 * @return
	 */
	@Bean
	UserClient client(WebClient.Builder builder) {
       var webClient = builder.baseUrl("https://jsonplaceholder.typicode.com").build();
	   var webClientAdapter = WebClientAdapter.forClient(webClient);

	   return HttpServiceProxyFactory.builder(webClientAdapter).build().createClient(UserClient.class);
	}

}
