package com.userservice.service;

import org.springframework.stereotype.Service;

import com.userservice.models.User;


@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

	@Override
	public void scheduleEmailConfirmation(User user) {
		System.out.print("ScheduleEmail confirmation is called");
		
	}

}
