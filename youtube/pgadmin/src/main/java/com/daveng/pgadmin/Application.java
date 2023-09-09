package com.daveng.pgadmin;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.daveng.pgadmin.event.Event;
import com.daveng.pgadmin.repository.EventRepository;

@SpringBootApplication
public class Application {

	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
	@Bean
	CommandLineRunner commandLineRunner(EventRepository repository) {
		return args -> {
			 if (repository.count() == 0) {
				 var event = Event.builder()
						 .name("Spring One at VMWare Explore")
						 .description("Description nomber one")
						 .cfpStartDate(LocalDate.of(2023, 8, 21))
						 .cfpEndDate(LocalDate.of(2023, 8, 24))
						 .startDate(LocalDate.now().minusDays(180))
						 .endDate(LocalDate.now().minusDays(90))
						 .location("Las Vegas ,NY")
						 .website("https://springone.io").build();
				 
				 repository.save(event);
				 log.info("Event Created  : " + event.getName());
			 }
		};
	}
}
