package com.dev.cinema.exceptions;

public class HashingFailedException extends RuntimeException {
    public HashingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public HashingFailedException(Throwable cause) {
        super(cause);
    }
}
