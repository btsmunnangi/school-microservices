package com.school.school;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class School {
@Id
private Integer id;
private String name;
private String email;
private Integer studentId;
private Integer reviewCount;

public School() {
	
}
public School(Integer id, String name, String email) {
	this.id = id;
	this.name = name;
	this.email = email;
}
public Integer getId() {
	return id;
}
public Integer getStudentId() {
	return studentId;
}
public void setStudentId(Integer studentId) {
	this.studentId = studentId;
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
public Integer getReviewCount() {
	return reviewCount;
}
public void setReviewCount(Integer reviewCount) {
	this.reviewCount = reviewCount;
}
}
