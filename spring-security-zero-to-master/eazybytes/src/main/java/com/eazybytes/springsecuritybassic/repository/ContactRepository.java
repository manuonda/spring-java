package com.eazybytes.springsecuritybassic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.springsecuritybassic.models.Contact;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}
