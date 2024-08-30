package com.example.demo.domain.exception;

public class InvalidPageIndexException extends RuntimeException{
    public InvalidPageIndexException(String message) {
        super(message);
    }
}
