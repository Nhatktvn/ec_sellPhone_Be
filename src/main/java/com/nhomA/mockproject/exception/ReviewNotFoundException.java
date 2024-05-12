package com.nhomA.mockproject.exception;

public class ReviewNotFoundException extends  RuntimeException{
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
