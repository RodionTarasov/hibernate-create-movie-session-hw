package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {

    public static void main(String[] args) {
        Injector injector = Injector.getInstance("mate.academy");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie matrix = new Movie();
        matrix.setTitle("Matrix");
        matrix.setDescription("Sci-Fi Action");
        movieService.add(matrix);
        movieService.get(matrix.getId());

        Movie inception = new Movie();
        inception.setTitle("Inception");
        inception.setDescription("Mind-bending thriller");
        movieService.add(inception);
        movieService.get(inception.getId());

        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        CinemaHall hall1 = new CinemaHall();
        hall1.setCapacity(100);
        hall1.setDescription("Main Hall");
        cinemaHallService.add(hall1);
        cinemaHallService.get(hall1.getId());

        CinemaHall hall2 = new CinemaHall();
        hall2.setCapacity(50);
        hall2.setDescription("Small Hall");
        cinemaHallService.add(hall2);
        cinemaHallService.get(hall2.getId());

        MovieSession session1 = new MovieSession();
        session1.setMovie(matrix);
        session1.setCinemaHall(hall1);
        session1.setShowTime(LocalDateTime.of(2025, 9, 15, 18, 0));
        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(session1);
        movieSessionService.get(session1.getId());

        MovieSession session2 = new MovieSession();
        session2.setMovie(inception);
        session2.setCinemaHall(hall2);
        session2.setShowTime(LocalDateTime.of(2025, 9, 15, 20, 0));
        movieSessionService.add(session2);
        movieSessionService.get(session2.getId());

        System.out.println("All movies:");
        movieService.getAll().forEach(System.out::println);

        System.out.println("All cinema halls:");
        cinemaHallService.getAll().forEach(System.out::println);

        System.out.println("Available sessions for Matrix on 2025-09-15:");
        movieSessionService.findAvailableSessions(matrix.getId(), LocalDate.of(2025, 9, 15))
                .forEach(System.out::println);

    }
}
