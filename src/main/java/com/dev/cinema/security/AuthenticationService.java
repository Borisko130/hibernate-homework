package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    /**
     * We should register a new user. New user entity will contains the email and password
     * @param email - user email. should be unique for each user
     * @param password - user password
     * @return new user instance
     */
    User register(String email, String password);
}
