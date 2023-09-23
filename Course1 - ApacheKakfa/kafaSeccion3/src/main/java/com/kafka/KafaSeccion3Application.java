package com.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

//https://hevodata.com/learn/kafka-batch-processing/

@SpringBootApplication
public class KafaSeccion3Application {


	@Value("${spring.kafka.topic.one}")
	String topicOne;

	@Value("${spring.kafka.topic.two}")
	String topicTwo;


	public static void main(String[] args) {
		SpringApplication.run(KafaSeccion3Application.class, args);
	}


	// https://github.com/spring-projects/spring-kafka/blob/main/samples/sample-01/src/main/java/com/example/Application.java
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {
		return args -> {
           for (var i = 0 ; i < 100 ; i++) {
			   kafkaTemplate.send("one", "Topic Send : " + i);
		   }
		};
	}
}
