package com.hoaxify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoaxify.models.User;


public interface UserRepository extends JpaRepository<User,Long>{

}
