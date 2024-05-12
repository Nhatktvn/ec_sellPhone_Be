package com.nhomA.mockproject.exception;

public class UserNameExistedException extends RuntimeException{

    public UserNameExistedException(String message) {
        super(message);
    }
}
