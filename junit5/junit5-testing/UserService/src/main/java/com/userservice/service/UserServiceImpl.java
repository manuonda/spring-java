package com.userservice.service;

import com.userservice.models.User;
import com.userservice.repository.UsersRepository;
import com.userservice.repository.UsersRepositoryImpl;

public class UserServiceImpl implements UserService{

	UsersRepository usersRepository;
	
	EmailVerificationService emailVerificationService;
	
	
	public UserServiceImpl(UsersRepository usersRepository , EmailVerificationService emailVerificationService) {
		super();
		this.emailVerificationService = emailVerificationService;
		this.usersRepository = usersRepository;
	}




	@Override
	public User createUser(String firstName, String lastName, String email, String password, String repeatPassword, String id)   {
		// TODO Auto-generated method stub
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setRepeatPassword(repeatPassword);
		user.setId(id);
		if (firstName == null || firstName.trim().length() == 0 ) {
			throw new IllegalArgumentException("User's first name is empty");
		}
		
		if (lastName == null || lastName.trim().length() == 0 ) {
			throw new IllegalArgumentException("User's last name is empty");
		}
	

		boolean isUserCreated = false;
		try {
	      isUserCreated = 	usersRepository.save(user); 		
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new UserServiceException(e.getMessage());
		}
		if(!isUserCreated ) throw new UserServiceException("Could not create User");
		

		try {
			emailVerificationService.scheduleEmailConfirmation(user);
		} catch (Exception e) {
			throw new UserServiceException(e.getMessage());
		}
		return user;
	}
	
	


}
