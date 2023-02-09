package com.userservice.service;

import com.userservice.models.User;

public interface EmailVerificationService {
  void scheduleEmailConfirmation(User user);
}
