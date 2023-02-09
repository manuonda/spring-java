package com.userservice.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.userservice.models.User;
import com.userservice.repository.UsersRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	/**
	 * Esta clase va a injectar todos los objects 
	 * mocks
	 */
	@InjectMocks
	UserServiceImpl userService;
	
	String firstName;
	String lastName;
	String email;
	String password;
	String repeatPassword;
	String id ;
	
	
	/**
	 * Para el object simulado no trabajamos 
	 * con la base de datos
	 */
	@Mock
	UsersRepository usersRepository;
	
	@Mock 
	EmailVerificationService emailVerificationService;
	
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
	
		Mockito.when(usersRepository.save(Mockito.any(User.class))).thenReturn(true);
		// Act
		User user = userService.createUser(firstName, lastName, email, password, repeatPassword, id);
		// Assert
		assertNotNull(user, " The createUser() should not return null");
		assertEquals(firstName, user.getFirstName(), "User's firstName is incorrect");
		assertEquals(lastName, user.getLastName(), "User's lastName is incorrect");
		assertEquals(email, user.getEmail(), "User's email is incorrect");
		assertNotNull(user.getId(), "User id is missing");
		
		Mockito.verify(usersRepository, Mockito.times(1)).save(Mockito.any(User.class));
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
	
	
	@DisplayName("If save() method causes RuntimeException, a UserServiceException is thrown")
	@Test
	void testCreatedUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
		//Arrange
		  when(usersRepository.save(Mockito.any(User.class))).thenThrow(RuntimeException.class);
		//Act
		 assertThrows(UserServiceException.class, ()->{
			 userService.createUser(firstName, lastName, email, password, repeatPassword, id);
		 } ,"Should have thrown UserServiceException instead");   
		
		//Assert
	}
	
	@DisplayName("EmailNotificationException is handled")
	@Test
	public void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException() 
	{
		//Arrange 
		when(usersRepository.save(Mockito.any(User.class))).thenReturn(true);
	
		doThrow(EmailVerificationException.class)
		.when(emailVerificationService)
		.scheduleEmailConfirmation(Mockito.any(User.class));
		
		// Act 
		assertThrows(UserServiceException.class, ()->{
			userService.createUser(firstName, lastName, email, password, repeatPassword,id);
		},"Should have thrown UserServiceException instead");
		
		
		verify(emailVerificationService,times(1)).scheduleEmailConfirmation(Mockito.any(User.class));
		
	}
	
	@DisplayName("Schedule Email Confirmation is received")
	@Test
	public void testCreateUser_whenUserCreated_schedulesEMailConfirmation() {
		//Arrange 
		when(usersRepository.save(Mockito.any(User.class))).thenReturn(true);
		
		doCallRealMethod().when(emailVerificationService)
		.scheduleEmailConfirmation(Mockito.any(User.class));
		
		//Act
		userService.createUser(firstName, lastName, email, password, repeatPassword, id);
		
		//Assert
		verify(emailVerificationService, times(1)).scheduleEmailConfirmation(Mockito.any(User.class));
	}


}
