package com.dev.cinema.security.impl;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        logger.info("User with email " + email + " tries to log in");
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isPresent() && checkPassword(userFromDb.get(), password)) {
            return userFromDb.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        logger.info("Registering new User with email " + email);
        User user = new User(email, password);
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }

    private boolean checkPassword(User user, String inputPassword) {
        String generatedPassword = HashUtil.hashPassword(inputPassword, user.getSalt());
        return generatedPassword.equals(user.getPassword());
    }
}
