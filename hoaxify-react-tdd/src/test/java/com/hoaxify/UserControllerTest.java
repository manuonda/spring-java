package com.hoaxify;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.hoaxify.models.User;
import com.hoaxify.repository.UserRepository;
import com.hoaxify.shared.GenericResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
 // @TestMethodOrder(MethodOrderer.MethodName.class)
public class UserControllerTest {

	private static final String API_1_0_USERS = "/api/1.0/users";
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Autowired
	UserRepository userRepository;
	
	
	@BeforeEach
	public  void cleanup() {
		userRepository.deleteAll();
	}
	@Test
    public void postUser_whenUserIsValid_receiveOk() {
		User user = new User();
		user = createValidateUser();
		ResponseEntity<Object> response = testRestTemplate.postForEntity( API_1_0_USERS, user, Object.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	private User createValidateUser() {
		User user = new User();
		user.setUserName("test-user");
		user.setDisplayName("test-display");
		user.setPassword("P4ssword");
		return user;
	}	
	
	@Test
	public void postUser_whenUserIsValid_userSavedToDatabase() {
		User use = createValidateUser();
		testRestTemplate.postForEntity(API_1_0_USERS,use, Object.class);
		assertThat(userRepository.count()).isEqualTo(1);
	}
	
	@Test
	public void postUser_whenUserIsValid_receiveReceiveSucessMessage() {
		User user = createValidateUser();
		ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
		assertThat(response.getBody().getMessage()).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}
	
	@Test
	public void postUser_whenUserIsValid_passwordIshHasheedInDatabase() {
		User user = createValidateUser();
		testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class );
		List<User> users =  userRepository.findAll();
		User inDB = users.get(0);
		assertThat(inDB.getPassword()).isNotEqualTo(user.getPassword());
	}
	
}
