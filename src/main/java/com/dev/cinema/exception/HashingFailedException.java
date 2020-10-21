package com.dev.cinema.exception;

public class HashingFailedException extends RuntimeException {
    public HashingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
