package com.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring6IvanlopezApplication  implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private UserClient userClient  ;

	
	public static void main(String[] args) {
		SpringApplication.run(Spring6IvanlopezApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	    userClient.allUsers().forEach(userPlaceHolder -> {
			System.out.println("Storing user : " + userPlaceHolder.name());
			this.userRepository.save(new User(null, userPlaceHolder.name()));
		}); 	
	}


	

	// @Override
	// public void run(String... args) throws Exception {
				
	// 	List.of("David", "Andres", "Raque", "Sofia").forEach( name -> {
	// 		userRepository.save(new User(null,name));
	// 	 });

	// }

}
