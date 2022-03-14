package by.cinema.services;

import by.cinema.entities.Movie;
import by.cinema.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);
    private MovieRepository movieRepository;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
        movieRepository.save(movie);
        log.info("добавление фильма " + movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
        log.info("фильм был удалён по ID " + id);
    }

    public Movie findUserById(Long movieId) {
        Optional<Movie> userFromDb = movieRepository.findById(movieId);
        return userFromDb.orElse(new Movie());
    }
}
