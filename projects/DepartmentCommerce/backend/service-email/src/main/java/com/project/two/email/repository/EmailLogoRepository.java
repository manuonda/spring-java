package com.project.two.email.repository;

import com.project.two.email.entity.domain.EmailLogo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailLogoRepository extends MongoRepository<EmailLogo, String> {
}
