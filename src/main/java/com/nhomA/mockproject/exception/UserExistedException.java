package com.nhomA.mockproject.exception;

public class UserExistedException extends RuntimeException{
    public UserExistedException(String message) {
        super(message);
    }
}
