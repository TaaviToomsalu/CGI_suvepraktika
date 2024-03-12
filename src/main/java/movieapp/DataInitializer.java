package movieapp;

import movieapp.model.Movie;
import movieapp.repository.MovieRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final MovieRepository movieRepository;

    public DataInitializer(MovieRepository movieRepository) {

        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void initializeData() {
        // Populate movies
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, "Movie 1", "Action", 120, "PG-13", LocalTime.of(10, 0), "English"));
        movies.add(new Movie(2L, "Movie 2", "Comedy", 90, "PG", LocalTime.of(12, 0), "English"));
        // Add more movies as needed

        // Save movies to the repository
        movieRepository.saveAll(movies);
    }
}