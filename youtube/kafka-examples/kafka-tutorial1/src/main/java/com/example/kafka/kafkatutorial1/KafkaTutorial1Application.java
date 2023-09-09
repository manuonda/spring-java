package com.example.kafka.kafkatutorial1;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.kafka.kafkatutorial1.config.KafkaConfigProps;
import com.example.kafka.kafkatutorial1.domain.CustomerVisitEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class KafkaTutorial1Application {

	@Autowired
	private ObjectMapper mapper ;
	public static void main(String[] args) {
		SpringApplication.run(KafkaTutorial1Application.class, args);
	}


	@Bean 
	public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate, final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {
        final CustomerVisitEvent event = CustomerVisitEvent.builder()
		.customerId(UUID.randomUUID().toString())
		.message("Message from customer")
		.build();
		final String payload = mapper.writeValueAsString(event);
		return args -> {
			kafkaTemplate.send(kafkaConfigProps.getTopic(),payload);
		};
	}

	@KafkaListener(topics = "customer.visit")
	public String listeners(final String in){
		System.out.println(in);
		return in;
	}

}
