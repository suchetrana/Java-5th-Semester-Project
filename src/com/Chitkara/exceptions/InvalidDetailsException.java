package com.Chitkara.exceptions;

public class InvalidDetailsException extends RuntimeException {
    public InvalidDetailsException(String message) {
        super(message);
    }
}
