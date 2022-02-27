package by.cinema.controllers;

import by.cinema.entities.Movie;
import by.cinema.entities.User;
import by.cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/Movie")
    public String getMovie(Model model) {
        model.addAttribute("movieAdd", new Movie());
        model.addAttribute("AllMovie", movieService.AllMovies());
        return "movie";
    }

    @PostMapping("/Movie/add")
    public String postMovie(@ModelAttribute(value = "movieAdd") Movie movie, Model model) {
        movieService.saveMovie(movie);
        return "redirect:/Movie";
    }

    @GetMapping("/Movie/delete/{id}")
    public String getDeleteMovie(@PathVariable(value = "id") Long movieId) {
        movieService.deleteMovie(movieId);
        return "redirect:/Movie";
    }

    @GetMapping("/Movie/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findUserById(id);
        model.addAttribute("movieForm", movie);
        return "updateMovie";
    }

    @PostMapping("/Movie/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, Movie movie, Model model) {
        movieService.saveMovie(movie);
        return "redirect:/Movie";
    }
}
