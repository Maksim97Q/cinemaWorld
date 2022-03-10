package by.cinema.controllers;

import by.cinema.entities.Movie;
import by.cinema.entities.User;
import by.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MovieController {
    private static final String REDIRECT_MOVIE = "redirect:/Movie";
    private static final String UPDATE_MOVIE = "updateMovie";
    private static final String MOVIE = "movie";

    @Autowired
    private MovieService movieService;

    @GetMapping("/Movie")
    public String getMovie(Model model, @RequestParam(value = "movieFilter", required = false) String movieFilter) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM");
//        Date date = new Date();
//        model.addAttribute("date", formatter.format(date));
        model.addAttribute("AllMovie", movieService.AllMoviesWithFilter(movieFilter));
        model.addAttribute("movieAdd", new Movie());
        model.addAttribute("movieFilter", movieFilter);
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
}
