package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");
    private static final MovieService movieService = (MovieService) injector
            .getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService = (CinemaHallService) injector
            .getInstance(CinemaHallService.class);
    private static final MovieSessionService movieSessionService = (MovieSessionService) injector
            .getInstance(MovieSessionService.class);
    private static final AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static final UserService userService = (UserService) injector
            .getInstance(UserService.class);
    private static final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    private static final OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious123");
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(3);
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setLocalDateTime(LocalDateTime.now());
        movieSession.setCinemaHall(cinemaHall);

        movieService.add(movie);
        movieService.getAll();
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll();

        movieSessionService.add(movieSession);
        LocalDate localDate = LocalDate.now();
        System.out.println(movieSessionService.findAvailableSessions(movie.getId(), localDate));

        User user = authenticationService.register("email", "pass");
        System.out.println(user.getPassword());
        User login = authenticationService.login(user.getEmail(), "pass");

        System.out.println(userService.findByEmail(user.getEmail()).get().getPassword());
        System.out.println(authenticationService.login(user.getEmail(), "pass"));

        shoppingCartService.addSession(movieSession, login);
        System.out.println(shoppingCartService.getByUser(login));

        Movie movie1 = new Movie();
        movie1.setTitle("new Movie");

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(4);

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(movie1);
        movieSession1.setLocalDateTime(LocalDateTime.now());
        movieSession1.setCinemaHall(cinemaHall1);

        movieService.add(movie1);
        cinemaHallService.add(cinemaHall1);
        movieSessionService.add(movieSession1);
        System.out.println(movieSessionService.findAvailableSessions(movie1.getId(),
                LocalDate.now()));

        shoppingCartService.addSession(movieSession1, login);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(login);
        System.out.println(shoppingCart);

        orderService.completeOrder(shoppingCart);
        (orderService.getOrdersHistory(user)).forEach(System.out::println);
    }
}
