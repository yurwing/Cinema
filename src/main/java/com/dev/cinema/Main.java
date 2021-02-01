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

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious123");
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(3);
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setLocalDateTime(LocalDateTime.now());
        movieSession.setCinemaHall(cinemaHall);
        MovieService movieService = (MovieService) injector
                .getInstance(MovieService.class);
        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        movieService.add(movie);
        movieService.getAll();
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll();
        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        LocalDate localDate = LocalDate.now();
        System.out.println(movieSessionService.findAvailableSessions(movie.getId(), localDate));
    }
}
