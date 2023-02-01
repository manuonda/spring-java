package com.userservice.repository;

import com.userservice.models.User;

public interface UsersRepository {
  
	boolean save(User user);
}
