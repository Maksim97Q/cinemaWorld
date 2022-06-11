package by.cinema.services;

import by.cinema.entities.BankCard;
import by.cinema.entities.Movie;
import by.cinema.entities.Ticket;
import by.cinema.entities.User;
import by.cinema.repositories.BankCardRepository;
import by.cinema.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    private MovieRepository movieRepository;
    private UserService userService;
    private BankCardRepository bankCardRepository;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBankCardRepository(BankCardRepository bankCardRepository) {
        this.bankCardRepository = bankCardRepository;
    }

    public List<Movie> AllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> AllMoviesWithFilter(String movieFilter) {
        if (movieFilter != null) {
            return movieRepository.findAll().stream()
                    .filter(p -> p.getName_Movie().contains(movieFilter))
                    .collect(Collectors.toList());
        } else {
            return movieRepository.findAll();
        }
    }

    public List<Movie> movieByDate(String date) {
        if (date != null) {
            return movieRepository.findAll().stream()
                    .filter(p -> p.getDates().contains(date))
                    .collect(Collectors.toList());
        } else {
            return movieRepository.findAll();
        }
    }

    public void saveMovie(Movie movie) {
        movie.setFree_places(20);
        movieRepository.save(movie);
        log.info("добавление фильма " + movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
        log.info("фильм был удалён по ID " + id);
    }

    public Movie findMovieById(Long movieId) {
        Optional<Movie> findMovie = movieRepository.findById(movieId);
        return findMovie.orElse(new Movie());
    }

    @Transactional
    public boolean buyTicketByMovie(Long id, Integer place_number) {
        User byUsername = userService.findByUsername(userService.getUser_log().getUsername());
        Movie movieById = findMovieById(id);
        BankCard byUserIdAndForPayment = bankCardRepository.findByUserIdAndForPayment(byUsername.getId());
        boolean find_seat = movieById.getTickets().stream()
                .anyMatch(place -> place.getPlaceNumber().equals(place_number));
        if (!find_seat && byUserIdAndForPayment != null
                && byUserIdAndForPayment.getBalance() >= movieById.getPrice()) {
            byUsername.setTicket(Collections.singleton(new Ticket(place_number, movieById, byUsername)));
            movieById.setFree_places(movieById.getFree_places() - 1);
            byUserIdAndForPayment.setBalance(byUserIdAndForPayment.getBalance() - movieById.getPrice());
            userService.saveUser(byUsername);
            bankCardRepository.save(byUserIdAndForPayment);
            return true;
        } else {
            return false;
        }
    }
}
