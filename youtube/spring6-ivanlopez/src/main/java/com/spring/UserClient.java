package com.spring;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;

public interface UserClient {
  
    @GetExchange("/users")
    List<UserPlaceHolder> allUsers();

    record UserPlaceHolder(String name) {
        
    }
}
