package com.demo.oauth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    	 createdUserId = userService.createUser(user);
    	
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
    	
    	// Assert 
    	assertEquals(newUserDetails.get("firstName"), updateUserDetails.get("firstName"),
    			"Returned value of user's first name is incorrect");
    	assertEquals(newUserDetails.get("lastName"), updateUserDetails.get("lastName"),
    			"The returned value of user's last name is incorrect");
    	
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {
  
    	// Act 
    	Map userDetails = userService.getUserDetails(createdUserId);
    	
    	// Assert 
    	assertNotNull(userDetails, "User details should not be null");
    	assertEquals(createdUserId, userDetails.get("userId"),
    			"Returned user details contains incorrect user id");
    	
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {

    	//Act
    	userService.deleteUser(createdUserId);
    	
    	//Assert 
    	assertNull(userService.getUserDetails(createdUserId),
    			"User  should not been found");
    }

}