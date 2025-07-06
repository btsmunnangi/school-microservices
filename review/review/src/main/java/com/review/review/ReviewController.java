package com.review.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.review.review.messaging.ReviewMessageProducer;

@RestController
@RequestMapping("/api/v1/reviews")
 public class ReviewController {
	private final ReviewService service;
	private final ReviewMessageProducer producer;

 public ReviewController(ReviewService service, ReviewMessageProducer producer) {
	this.service = service;
	this.producer=producer;
 }
@GetMapping
 public ResponseEntity<List<Review>> getAllReviews(@RequestParam Integer schoolId){
	 return new ResponseEntity<List<Review>>(service.findAllReviews(schoolId),HttpStatus.OK);
 }
 @GetMapping("/{id}")
 public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
	 return new ResponseEntity<Review>(service.findReview(id),HttpStatus.OK);
 }
 @PostMapping
 public ResponseEntity<String> addReview(@RequestBody Review review) {
	 producer.sendMessage(review);
	 return new ResponseEntity<String>(service.addReview(review),HttpStatus.OK);
 }
 @PutMapping("/{id}")
 public ResponseEntity<String> updateReview(@RequestBody Review review,@PathVariable Integer id) {
	 return new ResponseEntity<String>(service.updateReview(review, id),HttpStatus.OK);
 }
 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteReview(@PathVariable Integer id) {
	 return new ResponseEntity<String>(service.deleteReview(id),HttpStatus.OK);
 }
}
