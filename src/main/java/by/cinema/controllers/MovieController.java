package by.cinema.controllers;

import by.cinema.entities.Movie;
import by.cinema.entities.User;
import by.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class MovieController {
    private static final String REDIRECT_MOVIE = "redirect:/Movie";
    private static final String UPDATE_MOVIE = "updateMovie";
    private static final String MOVIE = "movie";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM");
    DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private MovieService movieService;

    @GetMapping("/Movie")
    public String getMovie(@RequestParam(value = "movieFilter", required = false) String movieFilter, Model model) {
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
        return MOVIE;
    }

    @GetMapping("/Movie/find_date_movie/{date_search}")
    public String getMovieFindDate(@PathVariable(value = "date_search") String date_search, Model model) {
        LocalDate localDate = LocalDate.now();
        model.addAttribute("date", formatter.format(localDate));
        model.addAttribute("date_1", formatter.format(localDate.plusDays(1)));
        model.addAttribute("date_2", formatter.format(localDate.plusDays(2)));
        model.addAttribute("date_form", formatter_1.format(localDate));
        model.addAttribute("date_form_1", formatter_1.format(localDate.plusDays(1)));
        model.addAttribute("date_form_2", formatter_1.format(localDate.plusDays(2)));
        model.addAttribute("AllMovie", movieService.movieByDate(date_search));
        model.addAttribute("movieAdd", new Movie());
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
        Movie movie = movieService.findUserById(id);
        model.addAttribute("movieForm", movie);
        return UPDATE_MOVIE;
    }

    @PostMapping("/Movie/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, Movie movie, Model model) {
        movieService.saveMovie(movie);
        return REDIRECT_MOVIE;
    }
//    @GetMapping(value = {"/Movie/{date_Search}", "/Movie"})
//    public String getMovie(@PathVariable(value = "date_Search", required = false) String date_Search,
//                           @RequestParam(value = "movieFilter", required = false) String movieFilter, Model model) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM");
//        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.now();
//        model.addAttribute("date", formatter.format(localDate));
//        model.addAttribute("date_1", formatter.format(localDate.plusDays(1)));
//        model.addAttribute("date_2", formatter.format(localDate.plusDays(2)));
//        model.addAttribute("date_form", formatter_1.format(localDate));
//        model.addAttribute("date_form_1", formatter_1.format(localDate.plusDays(1)));
//        model.addAttribute("date_form_2", formatter_1.format(localDate.plusDays(2)));
//        model.addAttribute("AllMovie", movieService.AllMoviesWithFilter(movieFilter));
//        model.addAttribute("AllMovie", movieService.movieByDate(date_Search));
//        model.addAttribute("movieAdd", new Movie());
//        model.addAttribute("movieFilter", movieFilter);
//        model.addAttribute("date_Search", date_Search);
//        return MOVIE;
//    }
}
