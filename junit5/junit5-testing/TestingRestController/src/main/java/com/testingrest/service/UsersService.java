package com.testingrest.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.testingrest.shared.UserDto;

import java.util.List;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto user);
    List<UserDto> getUsers(int page, int limit);
    UserDto getUser(String email);
}
