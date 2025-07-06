package com.review.review;

import java.util.List;

public interface ReviewService {
 public List<Review> findAllReviews(Integer schoolId);
 public Review findReview(Integer id);
 public String addReview(Review review);
 public String updateReview(Review review,Integer id);
 public String deleteReview(Integer id);
}
