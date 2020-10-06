package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall hall = new CinemaHall();
        hall.setCapacity(200);
        cinemaHallService.add(hall);

        MovieSession morningSession = new MovieSession();
        morningSession.setCinemaHall(hall);
        morningSession.setMovie(movie);
        morningSession.setShowTime(LocalDateTime.of(
                2020, Month.OCTOBER, 6, 10, 20));
        movieSessionService.add(morningSession);
        MovieSession eveningSession = new MovieSession();
        eveningSession.setCinemaHall(hall);
        eveningSession.setMovie(movie);
        eveningSession.setShowTime(LocalDateTime.of(
                2020, Month.OCTOBER, 6, 22, 20));
        movieSessionService.add(eveningSession);

        List<MovieSession> sessionList
                = movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now());
        System.out.println(sessionList);
    }
}
