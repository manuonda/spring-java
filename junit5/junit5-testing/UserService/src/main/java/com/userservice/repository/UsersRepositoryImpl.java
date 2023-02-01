package com.userservice.repository;

import java.util.HashMap;
import java.util.Map;

import com.userservice.models.User;

public class UsersRepositoryImpl  implements UsersRepository{

	Map<String, User> users = new HashMap<>();
	
	@Override
	public boolean save(User user) {
		if (!users.containsKey(user.getId())) {
			users.put(user.getId(), user); 
			return true;
		}
		return false;
	}

	
}
