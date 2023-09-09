package com.project1.school;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;



@Configuration
//@EnableWebFlux
public class WebFluxConfig {
  
	@Value("${application.config.students-url}")
	private String url;

//	@Bean
//	public WebClient getWebClient() {
//		return WebClient.builder()
//				.baseUrl(url)
//				//.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
//				.build();
//	}
}
