package com.nhomA.mockproject.exception;

public class CartLineItemNotFoundException extends RuntimeException{
    public CartLineItemNotFoundException(String message) {
        super(message);
    }
}
