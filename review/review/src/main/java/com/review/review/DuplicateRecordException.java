package com.review.review;

public class DuplicateRecordException extends RuntimeException{
	private static final long serialVersionUID = 1L;
    public DuplicateRecordException(String message) {
    	super(message);
    }
}
