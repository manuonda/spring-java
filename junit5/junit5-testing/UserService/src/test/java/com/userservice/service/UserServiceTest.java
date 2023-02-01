package com.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.userservice.models.User;
import com.userservice.repository.UsersRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;
	
	String firstName;
	String lastName;
	String email;
	String password;
	String repeatPassword;
	String id ;
	
	@Mock
	UsersRepository usersRepository;
	
	@BeforeEach
	void init() {
	     
		 firstName = "Sergey";
		 lastName = "Kargopoloy";
		 email = "test@test.com";
		 password = "12345678";
		 repeatPassword = "12345678";
		 id = UUID.randomUUID().toString();
	}
	
	@DisplayName("User object created")
	@Test
	void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
		// Arrange
	
		
		// Act
		User user = userService.createUser(firstName, lastName, email, password, repeatPassword, id);
		// Assert
		assertNotNull(user, " The createUser() should not return null");
		assertEquals(firstName, user.getFirstName(), "User's firstName is incorrect");
		assertEquals(lastName, user.getLastName(), "User's lastName is incorrect");
		assertEquals(email, user.getEmail(), "User's email is incorrect");
		assertNotNull(user.getId(), "User id is missing");
	}

	@DisplayName("Empty first names causes correct exception")
	@Test
	void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
		// Arrange
		firstName = "";
		String expectedExceptionMessage = "User's first name is empty";
		// Act
		//User user = userService.createUser(firstName, lastName, email, password, repeatPassword, id);
		// Assert
	    IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, ()->{
	    	 userService.createUser(firstName, lastName, email, password, repeatPassword, id); 
	    },"Empty first name should have caused an Illegal Argument Exception");
	    
	    // assert
	    assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception error message is not correct");
	}

}
