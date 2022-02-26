package by.cinema.services;

import by.cinema.entities.Movie;
import by.cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> AllMovies() {
        return movieRepository.findAll();
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
