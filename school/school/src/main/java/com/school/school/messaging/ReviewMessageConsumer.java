package com.school.school.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.school.school.ReviewMessage;
import com.school.school.SchoolService;
@Service
public class ReviewMessageConsumer {
    public final SchoolService schoolService;

	public ReviewMessageConsumer(SchoolService schoolService) {
		this.schoolService = schoolService;
	}
    @RabbitListener(queues ="schoolRatingQueue")
	public void consumeMessage(ReviewMessage message) {
    	schoolService.updateSchoolRating(message);
	}
}
