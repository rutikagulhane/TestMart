package com.example.TestMart.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String s) {
        super(s);
    }
}
