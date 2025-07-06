package com.review.review.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.review.review.Review;
import com.review.review.ReviewMessage;
@Service
public class ReviewMessageProducer {
	private final RabbitTemplate template;

	public ReviewMessageProducer(RabbitTemplate template) {
		this.template = template;
	}
	public void sendMessage(Review review) {
		ReviewMessage message = new ReviewMessage();
		message.setId(review.getId());
		message.setDescription(review.getDescription());
		message.setTitle(review.getTitle());
		message.setRating(review.getRating());
		message.setSchoolId(review.getSchoolId());
		template.convertAndSend("schoolRatingQueue",message);
	}
   
}
