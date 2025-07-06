package com.student.student;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
	public StudentController(StudentService service) {
		this.service = service;
	}
	private StudentService service;
	@GetMapping
	public ResponseEntity<List<Student>> findAllStudent(){
		return ResponseEntity.ok(service.findAllStudent());
	}
	@PostMapping
	public ResponseEntity<String>  saveStudent(@RequestBody Student s) {
		return ResponseEntity.ok(service.saveStudent(s));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getStudent(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateStudent(@RequestBody Student st,@PathVariable Integer id) {
		return ResponseEntity.ok(service.updateStudent(st, id));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
		return ResponseEntity.ok(service.deleteStudent(id));
	}
}
