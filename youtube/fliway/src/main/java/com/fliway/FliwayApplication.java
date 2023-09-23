package com.fliway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FliwayApplication implements CommandLineRunner{

	private final BookDepository bookDepository;

	public FliwayApplication(BookDepository bookDepository){
       this.bookDepository = bookDepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FliwayApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
