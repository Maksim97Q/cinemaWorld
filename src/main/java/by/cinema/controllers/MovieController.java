package by.cinema.controllers;

import by.cinema.entities.Movie;
import by.cinema.entities.Ticket;
import by.cinema.entities.User;
import by.cinema.services.MovieService;
import by.cinema.services.TicketService;
import by.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MovieController {
    private static final String REDIRECT_MOVIE = "redirect:/Movie";
    private static final String REDIRECT_MOVIE_SHOW = "redirect:/Movie/show/";
    private static final String UPDATE_MOVIE = "updateMovie";
    private static final String TICKET = "ticket";
    private static final String MOVIE = "movie";

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @GetMapping(value = {"/Movie/find_date_movie/{date_Search}", "/Movie"})
    public String getMovie(@PathVariable(value = "date_Search", required = false) String date_Search,
                           @RequestParam(value = "movieFilter", required = false) String movieFilter, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM");
        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        model.addAttribute("date", formatter.format(localDate));
        model.addAttribute("date_1", formatter.format(localDate.plusDays(1)));
        model.addAttribute("date_2", formatter.format(localDate.plusDays(2)));
        model.addAttribute("date_form", formatter_1.format(localDate));
        model.addAttribute("date_form_1", formatter_1.format(localDate.plusDays(1)));
        model.addAttribute("date_form_2", formatter_1.format(localDate.plusDays(2)));
        model.addAttribute("AllMovie", movieService.AllMoviesWithFilter(movieFilter));
        model.addAttribute("movieAdd", new Movie());
        model.addAttribute("movieFilter", movieFilter);
        if (movieFilter == null) {
            model.addAttribute("AllMovie", movieService.movieByDate(date_Search));
            model.addAttribute("date_Search", date_Search);
        }
        return MOVIE;
    }

    @PostMapping("/Movie/add")
    public String postMovie(@ModelAttribute(value = "movieAdd") Movie movie, Model model) {
        movieService.saveMovie(movie);
        return REDIRECT_MOVIE;
    }

    @GetMapping("/Movie/delete/{id}")
    public String getDeleteMovie(@PathVariable(value = "id") Long movieId) {
        movieService.deleteMovie(movieId);
        return REDIRECT_MOVIE;
    }

    @GetMapping("/Movie/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findMovieById(id);
        model.addAttribute("movieForm", movie);
        return UPDATE_MOVIE;
    }

    @PostMapping("/Movie/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, Movie movie, Model model) {
        movieService.saveMovie(movie);
        return REDIRECT_MOVIE;
    }

    @GetMapping("/Movie/show/{id}")
    public String showOneMovie(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("movie_id", movieService.findMovieById(id));
        return TICKET;
    }

    @GetMapping("/Movie/show/{id}/buy/{seat}")
    public String buyTicketAtMovie(@PathVariable(value = "seat") Integer seat,
                                   @PathVariable(value = "id") Long id, Model model) {
        User byUsername = userService.findByUsername(userService.getUser_log().getUsername());
        Movie movieById = movieService.findMovieById(id);
        if (byUsername.getTicket() == null) {
            byUsername.setTicket(new Ticket(seat, movieById));
            movieById.setSeats(movieById.getSeats() - 1);
            userService.saveUser(byUsername);
            System.out.println("куплено!");
        } else {
            System.out.println("ERROR");
        }
        return REDIRECT_MOVIE_SHOW + movieById.getId();
    }
}
