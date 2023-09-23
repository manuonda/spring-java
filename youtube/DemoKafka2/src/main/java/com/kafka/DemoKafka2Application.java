package com.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.core.KafkaTemplate;

// https://thepracticaldeveloper.com/spring-boot-kafka-config/

@SpringBootApplication
public class DemoKafka2Application {

	@Value("${spring.kafka.topic.one}")
	String topicOne;

	@Value("${spring.kafka.topic.two}")
	String topicTwo;

	public static void main(String[] args) {
		SpringApplication.run(DemoKafka2Application.class, args);
	}

	// https://github.com/spring-projects/spring-kafka/blob/main/samples/sample-01/src/main/java/com/example/Application.java
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {
		return args -> {
			//for (var i = 0; i < 100; i++) {
				kafkaTemplate.send("prueba2","Hola amigos String");
				kafkaTemplate.send("manuonda32", 42);
				//kafkaTemplate.send("manuonda32","Hola manuonda : " + i);
			//}

		};
	}
}
