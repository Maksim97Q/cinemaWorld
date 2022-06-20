package by.cinema.services;

import by.cinema.entities.BankCard;
import by.cinema.entities.Movie;
import by.cinema.entities.Ticket;
import by.cinema.entities.User;
import by.cinema.repositories.BankCardRepository;
import by.cinema.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {
    @Mock
    private static MovieRepository movieRepository;
    @InjectMocks
    private static MovieService movieService;
    @Mock
    private static UserService userService;
    @Mock
    private static BankCardRepository bankCardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void allMovies() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "1+1", "12-12-2022", 7, 20));
        when(movieService.AllMovies()).thenReturn(movieList);
        List<Movie> movies = movieService.AllMovies();
        assertNotNull(movies);
    }

    @Test
    void allMoviesWithFilter() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "1+1", "12-12-2022", 7, 20));
        movieList.add(new Movie(1L, "---", "12-12-2022", 7, 20));
        when(movieRepository.findAll()).thenReturn(movieList);
        List<Movie> collect = movieRepository.findAll().stream()
                .filter(p -> p.getName_Movie().contains("1"))
                .collect(Collectors.toList());
        List<Movie> movies = movieService.AllMoviesWithFilter("1");
        assertEquals(movies, collect);
        assertEquals(1, collect.size());
    }

    @Test
    void allMoviesWithFilterReturnFindAll() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "1+1", "12-12-2022", 7, 20));
        movieList.add(new Movie(1L, "---", "12-12-2022", 7, 20));
        when(movieService.AllMoviesWithFilter(null)).thenReturn(movieList);
        List<Movie> movies = movieService.AllMoviesWithFilter(null);
        assertEquals(2, movies.size());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void movieByDate() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "1+1", "12-12-2022", 3, 22));
        movieList.add(new Movie(2L, "--", "11-12-2022", 3, 22));
        when(movieRepository.findAll()).thenReturn(movieList);
        List<Movie> collect = movieRepository.findAll().stream()
                .filter(p -> p.getDates().contains("11-12-2022"))
                .collect(Collectors.toList());
        movieService.movieByDate("11-12-2022");
        assertEquals(1, collect.size());
    }

    @Test
    void movieByDateReturnFindAll() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "1+1", "12-12-2022", 3, 22));
        movieList.add(new Movie(2L, "--", "11-12-2022", 3, 22));
        when(movieService.movieByDate(null)).thenReturn(movieList);
        List<Movie> movies = movieService.movieByDate(null);
        assertEquals(2, movies.size());
        verify(movieRepository, times(1)).findAll();

    }

    @Test
    void saveMovie() {
        Movie movie = new Movie(1L, "1+1", "12-12-2022", 7, 20);
        movieService.saveMovie(movie);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    void deleteMovie() {
        movieService.deleteMovie(anyLong());
        verify(movieRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findMovieById() {
        Movie movie = new Movie(1L, "1+1", "12-12-2022", 7, 20);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        Movie movie1 = movieService.findMovieById(1L);
        verify(movieRepository, times(1)).findById(1L);
        assertNotNull(movie1);
        assertEquals(movie, movie1);
    }

    @Test
    void buyTicketByMovie() {
//        User user = new User(1L, "max", "234234");
//        Set<Ticket> ticketSet = new HashSet<>();
//        Ticket ticket = new Ticket(13, new Movie(), new User());
//        ticketSet.add(ticket);
//        BankCard bankCard = new BankCard(1L, 66L, 10, "active", true, new User());
//        Movie movie = new Movie(1L, "1+1", "12-12-2022", 7, 20, ticketSet);
//        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
//        when(bankCardRepository.findByUserIdAndForPayment(anyLong())).thenReturn(bankCard);
//        when(userService.findByUsername(user.getUsername())).thenReturn(user);
//        User byUsername = userService.findByUsername(user.getUsername());
//        Optional<Movie> movieById = movieRepository.findById(movie.getId());
//        assertTrue(movieById.isPresent());
//        BankCard byUserIdAndForPayment = bankCardRepository.findByUserIdAndForPayment(byUsername.getId());
//        boolean find_seat = movieById.get().getTickets().stream()
//                .anyMatch(place -> place.getPlaceNumber().equals(10));
//        assertFalse(find_seat);
//        assertNotNull(byUserIdAndForPayment);
//        assertTrue(byUserIdAndForPayment.getBalance() >= movieById.get().getPrice());
//        verify(userService, times(1)).saveUser(byUsername);
//        movieService.buyTicketByMovie(1L, 234);
//        verify(bankCardRepository, times(1)).save(bankCard);
    }
}