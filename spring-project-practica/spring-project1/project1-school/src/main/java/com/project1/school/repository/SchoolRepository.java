package com.project1.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.school.models.School;

public interface SchoolRepository extends JpaRepository<School, Long>{

}
