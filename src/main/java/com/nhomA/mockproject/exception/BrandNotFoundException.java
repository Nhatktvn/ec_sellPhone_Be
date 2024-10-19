package com.nhomA.mockproject.exception;

public class BrandNotFoundException extends RuntimeException{
    public BrandNotFoundException(String message) {
        super(message);
    }
}
