package com.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@SpringBootApplication
public class Seccion5Application {

	public static void main(String[] args) {
		SpringApplication.run(Seccion5Application.class, args);
	}
	private static final Logger logger = LoggerFactory.getLogger(Listener.class);

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate,
										KafkaListenerEndpointRegistry registry
	) {
		return args -> {
			CompletableFuture<SendResult<String,String>> future = kafkaTemplate.send("topicOne", "prueba onda");
			future.whenComplete(new BiConsumer<SendResult<String, String>, Throwable>() {
				@Override
				public void accept(SendResult<String, String> stringStringSendResult, Throwable throwable) {
                      System.out.println("resutlado : "+ stringStringSendResult.getProducerRecord());
					  System.out.println("v1 " + stringStringSendResult.toString());
				}
			});

			for( var i = 0; i < 10000; i++) {
				// forma syncrona
			 	//kafkaTemplate.send("topicOne", "Segundo datos").get(100, TimeUnit.MILLISECONDS);
			    kafkaTemplate.send("topicOne", String.valueOf(i),"valor : "+ i );
			}

			// Permite dormir 5 segundos
			logger.info("Waiting to start");
			Thread.sleep(2000);
			logger.info("Starting");
			// get listener container registry permite inicializar
			// de nuevo los mesinsages
			registry.getListenerContainer("manuonda").start();
			Thread.sleep(1000);
			logger.info("Stop");
			registry.getListenerContainer("manuonda").stop();
			Thread.sleep(2000);
			registry.getListenerContainer("manuonda").start();

		};
	}
}
