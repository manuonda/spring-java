package com.micro.school.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.school.dto.StudentDTO;


@FeignClient(name="student-service", url = "${application.config.students-url}")
public interface StudentClient {
   
	 @GetMapping("/school-id/{id}")
	 List<StudentDTO> findByIdSchool(@PathVariable Long id);
}
