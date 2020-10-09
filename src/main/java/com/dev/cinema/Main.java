package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private static AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    public static void main(String[] args) throws AuthenticationException {
        authenticationService.register("email@mail.net", "1234");
        User user = userService.findByEmail("email@mail.net").get();
        MovieSession movieSession = new MovieSession();
        shoppingCartService.addSession(movieSession, user);
        System.out.println("\n---GETTING CART---");
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        /*System.out.println("\n---CART WITH TICKETS---");
        System.out.println(shoppingCart);
        System.out.println("\n---CLEARING CART---");
        shoppingCartService.clear(shoppingCart);
        System.out.println("\n---EMPTY CART---");
        System.out.println(shoppingCart);*/
    }
}
