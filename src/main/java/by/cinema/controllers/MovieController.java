package by.cinema.controllers;

import by.cinema.entities.Movie;
import by.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {
    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/Movie")
    public String getMovie(Model model) {
        model.addAttribute("movieAdd", new Movie());
        model.addAttribute("AllMovie", movieService.AllMovies());
        return "movie";
    }

    @PostMapping("/Movie")
    public String postMovie(@ModelAttribute(value = "movieAdd") Movie movie, Model model) {
        movieService.saveMovie(movie);
        return "movie";
    }
}
