package com.school.school;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="student-service" ,url= "${school-service.url}")
public interface StudentClient {
	@GetMapping("/api/v1/students/{id}")
	Student getStudent(@PathVariable("id") Integer id);
}
