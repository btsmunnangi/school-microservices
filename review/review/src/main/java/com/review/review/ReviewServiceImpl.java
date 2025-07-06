package com.review.review;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository repo;
    private final static Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
	public ReviewServiceImpl(ReviewRepository repo) {
	  this.repo = repo;
	}

	@Override
	public List<Review> findAllReviews(Integer schoolId) {
		logger.info("ReviewServiceImpl::findAllReviews");
		return repo.findBySchoolId(schoolId);
	}

	@Override
	public Review findReview(Integer id) {
		logger.info("ReviewServiceImpl::findReview:"+id);
		return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Review Record Is Not Found With ID:"+id));
	}

	@Override
	public String addReview(Review review) {
		logger.info("ReviewServiceImpl::addReview:"+review.getId());
		if(repo.existsById(review.getId())) {
			throw new DuplicateRecordException("Review Record Already Exists:"+review.getId());
		}else {
			repo.save(review);
			return "Review Added";
		}
	}

	@Override
	public String updateReview(Review review, Integer id) {
		logger.info("ReviewServiceImpl::updateReview:"+id);
		if(repo.existsById(id)) {
			Review rvw = repo.findById(id).get();
			rvw.setTitle(review.getTitle());
			rvw.setDescription(review.getDescription());
			rvw.setRating(review.getRating());
			rvw.setSchoolId(review.getSchoolId());
			repo.save(rvw);
			return "Review Updated";
		}else {
			throw new ResourceNotFoundException("Review Record Is Not Found With ID:"+id);
		}
	}

	@Override
	public String deleteReview(Integer id) {
		logger.info("ReviewServiceImpl::deleteReview:"+id);
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return "Review Deleted";
		}else {
			throw new ResourceNotFoundException("Review Record Is Not Found With ID:"+id);
		}
	}

}
