package com.userservice.service;

import com.userservice.models.User;

public interface UserService {

	User createUser(String firstName,String lastName,String email,String password,String repeatPassword, String id);
}
