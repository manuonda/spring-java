package com.demo.oauth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.*;

import com.demo.oauth.io.UsersDatabase;
import com.demo.oauth.io.UsersDatabaseMapImpl;
import com.demo.oauth.service.UserService;
import com.demo.oauth.service.UserServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

	UsersDatabase userDatabase;
	UserService userService;
	String createdUserId;
	
    @BeforeAll
    void setup() {
        // Create & initialize database
    	userDatabase = new UsersDatabaseMapImpl();
    	userDatabase.init();
    	userService = new UserServiceImpl(userDatabase);
    }

    @AfterAll
    void cleanup() {
        // Close connection
        // Delete database
    	userDatabase.close();
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {
    	//Arrange
    	Map<String, String> user = new HashMap<>();
    	user.put("firstName", "Sergey");
    	user.put("lastName", "Kargopoly");
    	
    	// Act
    	String createdUserId = userService.createUser(user);
    	
    	// Assert
    	assertNotNull(createdUserId, "User id should not be null");

    }


    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {

    	// Arrange 
    	Map<String, String> newUserDetails = new HashMap<>();
    	newUserDetails.put("firstName", "John");
    	newUserDetails.put("lastName","Kargopolox");
    	
    	//Act
    	Map updateUserDetails = userService.updateUser(createdUserId, newUserDetails);
    	
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {

    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {

    }

}