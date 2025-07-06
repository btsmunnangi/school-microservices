package com.review.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes=ReviewController.class)
public class ReviewExceptionHanler {
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exc) {
         return new ResponseEntity<String>(exc.getMessage(),HttpStatus.NOT_FOUND);	
    }
	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<String> handleDuplicateRecordException(DuplicateRecordException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT); 
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleOtherExceptions(Exception ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
