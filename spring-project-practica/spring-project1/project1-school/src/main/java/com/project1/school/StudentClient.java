package com.project1.school;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project1.school.dto.StudentDTO;

@FeignClient(name="student-service", url = "${application.config.students-url}")
public interface StudentClient {
   
	
	@GetMapping("/school/{school-id}")
	public List<StudentDTO>  foundAllStudentsByIdSchool(@PathVariable("school-id") Long idSchool);
}
