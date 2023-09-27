package com.kafka;


import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;


// https://mehmetozkaya.medium.com/monitor-spring-boot-custom-metrics-with-micrometer-and-prometheus-using-docker-62798123c714
@SpringBootApplication
@EnableScheduling
public class Seccion5Application {

	@Autowired
	private PrometheusMeterRegistry prometheusMeterRegistry;

	@Autowired
	private MeterRegistry metegerRegister;

	@Autowired
	private KafkaTemplate kafkaTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Seccion5Application.class, args);
	}
	private static final Logger logger = LoggerFactory.getLogger(Listener.class);

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate,
										KafkaListenerEndpointRegistry registry
										//MeterRegistryConfig meterRegistryConfig
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

			/*for( var i = 0; i < 200; i++) {
				// forma syncrona
			 	//kafkaTemplate.send("topicOne", "Segundo datos").get(100, TimeUnit.MILLISECONDS);
			    kafkaTemplate.send("topicOne", String.valueOf(i),"valor : "+ i );
			}*/

			// Permite dormir 5 segundos
			// logger.info("Waiting to start");
			// Thread.sleep(2000);
			// logger.info("Starting");
			// get listener container registry permite inicializar
			// de nuevo los mesinsages
			// registry.getListenerContainer("manuonda").start();
			// Thread.sleep(1000);
			/// logger.info("Stop");
			// registry.getListenerContainer("manuonda").stop();
			 // Thread.sleep(2000);
			// registry.getListenerContainer("manuonda").start();

		};
	}

	@Scheduled(fixedDelay = 2000, initialDelay = 100)
	public void sendKafkaMessage(){
		for( int i = 0 ; i < 200; i++) {
			kafkaTemplate.send("topicOne", String.valueOf(i),"valor : "+ i );
		}
	}

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void print(){
     logger.info("Informacion cada 5 segundos");
		List<Meter> metrics = this.metegerRegister.getMeters();
		for( Meter meter: metrics) {
			logger.info("Meter {} ", meter.getId().getName());
		}
		double count = this.metegerRegister.get("kafka.producer.record.send.total").functionCounter().count();
		logger.info("Count {}", count);

	   /*double count2 = this.metegerRegister.get("spring.kafka.listener ")
				.tag("customTag", "customTagValue")
				.tag("spring.id", "myProducerFactory.myClientId-1")
				.functionCounter()
				.count();

		logger.info("Count 2 {}", count2);
	    */
	}
}
