package com.example.sma.exception;

public class NotFoundEmployeeException extends RuntimeException {
    public NotFoundEmployeeException(String msg) {
        super(msg);
    }
}
