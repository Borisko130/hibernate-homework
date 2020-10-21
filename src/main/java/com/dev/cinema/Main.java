package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    private static final AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);
    private static final UserService userService
            = context.getBean(UserService.class);
    private static final AuthenticationService authenticationService
            = context.getBean(AuthenticationService.class);
    private static final CinemaHallService cinemaHallService
            = context.getBean(CinemaHallService.class);
    private static final MovieService movieService
            = context.getBean(MovieService.class);
    private static final OrderService orderService
            = context.getBean(OrderService.class);
    private static final ShoppingCartService shoppingCartService
            = context.getBean(ShoppingCartService.class);

    public static void main(String[] args) throws AuthenticationException {
        authenticationService.register("email@mail.net", "1234");
        try {
            authenticationService.login("email@mail.net", "1234");
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(20);
        cinemaHall.setDescription("Arthouse hall");
        cinemaHallService.add(cinemaHall);
        logger.info(cinemaHallService.getAll());

        Movie movie = new Movie();
        movie.setTitle("007");
        movie.setDescription("Bond. James Bond");
        movieService.add(movie);
        logger.info(movieService.getAll());

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.now());
        User user = userService.findByEmail("email@mail.net").get();
        shoppingCartService.addSession(movieSession, user);
        logger.info(shoppingCartService.getByUser(user));

        logger.info("FIRST ORDER");
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        logger.info(orderService.completeOrder(shoppingCart.getTickets(),
                shoppingCart.getUser()));

        logger.info("SECOND ORDER");
        shoppingCartService.addSession(movieSession, user);
        shoppingCart = shoppingCartService.getByUser(user);
        logger.info(orderService.completeOrder(shoppingCart.getTickets(),
                shoppingCart.getUser()));

        logger.info(orderService.getOrderHistory(user));
    }
}
