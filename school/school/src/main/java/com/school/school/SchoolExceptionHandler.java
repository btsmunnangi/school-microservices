package com.school.school;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice(assignableTypes= {SchoolController.class})
public class SchoolExceptionHandler {
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
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException  ec){
		Map<String,String> errors = new HashMap<String,String>();
		ec.getBindingResult().getFieldErrors().forEach(error->{
			errors.put(error.getField(),error.getDefaultMessage());
		});
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
