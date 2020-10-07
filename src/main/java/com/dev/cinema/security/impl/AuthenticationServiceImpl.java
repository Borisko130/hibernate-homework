package com.dev.cinema.security.impl;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isPresent() && checkPassword(userFromDb.get(), password)) {
            return userFromDb.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        byte[] salt = HashUtil.getSalt();
        String pass = HashUtil.hashPassword(password, salt);
        return userService.add(new User(email, pass, salt));
    }

    private boolean checkPassword(User user, String inputPassword) {
        String generatedPassword = HashUtil.hashPassword(inputPassword, user.getSalt());
        return generatedPassword.equals(user.getPassword());
    }
}
