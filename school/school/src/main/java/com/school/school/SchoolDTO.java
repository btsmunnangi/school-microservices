package com.school.school;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SchoolDTO {
	private Integer id;
	@NotBlank(message = "School name is required")
	@Size(min = 2, max=100, message="School name should be between 2 and 100 characters" )
	private String name;
	@NotBlank(message ="Email is Required")
	@Email(message ="Email format is Invalid")
	private String email;
	private Student student;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

}
