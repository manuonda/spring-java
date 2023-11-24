package com.project.two.email.repository;

import com.project.two.email.entity.domain.EmailLogger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailLoggerRepository extends MongoRepository<EmailLogger, String> {
}
