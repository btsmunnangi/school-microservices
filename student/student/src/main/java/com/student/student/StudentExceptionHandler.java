package com.student.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice(assignableTypes ={StudentController.class})
public class StudentExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND); 
	}
	@ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<String> handleDuplicateRecordException(DuplicateRecordException ecx) {
    	return new ResponseEntity<String>(ecx.getMessage(),HttpStatus.CONFLICT); 
    }
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleOtherException(Exception ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
