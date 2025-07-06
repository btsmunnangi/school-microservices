package com.review.review;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Review {
	@Id
	private Integer id;
	private String title;
	private String description;
	private Double rating;
	private Integer schoolId;
	public Review() {
	}
	public Review(Integer reviewId, String title, String description, Double rating) {
		this.id = reviewId;
		this.title = title;
		this.description = description;
		this.rating = rating;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
}
