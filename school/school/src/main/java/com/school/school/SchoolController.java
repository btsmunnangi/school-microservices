package com.school.school;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {
  SchoolService service;

	public SchoolController(SchoolService service) {
		this.service = service;
	}
	@GetMapping
	public ResponseEntity<List<SchoolDTO>> getAllSchools(){
		return ResponseEntity.ok(service.findAllSchools());
	}
	@PostMapping
	public ResponseEntity<String> saveSchool(@Valid @RequestBody School school) {
		return ResponseEntity.ok(service.saveSchool(school));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSchool(@PathVariable Integer id) {
		return ResponseEntity.ok(service.deleteSchool(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateSchool(@RequestBody School sc,@PathVariable Integer id) {
		return ResponseEntity.ok(service.updateSchool(sc, id));
	}
	@GetMapping("/{id}")
	public ResponseEntity<SchoolDTO> getSchoolById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getSchoolById(id));
	}
	}
