package com.dev.cinema.exceptions;

public class HibernateConnectionException extends RuntimeException{
    public HibernateConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
