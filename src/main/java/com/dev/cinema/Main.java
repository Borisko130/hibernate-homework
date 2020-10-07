package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private static AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);

    public static void main(String[] args) throws AuthenticationException {
        authenticationService.register("email@mail.net", "1234");
        System.out.println(userService.findByEmail("email@mail.net"));
        System.out.println("\n\n");
        System.out.println(authenticationService.login("email@mail.net", "1234"));
    }
}
