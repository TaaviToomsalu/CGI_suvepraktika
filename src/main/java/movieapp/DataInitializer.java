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
        movies.add(new Movie(1, "Movie 1", "Action", 120, "PG-13", LocalTime.of(10, 0), "English"));
        movies.add(new Movie(2, "Movie 2", "Comedy", 90, "PG", LocalTime.of(12, 0), "English"));
        movies.add(new Movie(3, "Movie 3", "Drama", 110, "PG-13", LocalTime.of(14, 0), "Spanish"));
        movies.add(new Movie(4, "Movie 4", "Thriller", 100, "R", LocalTime.of(16, 0), "French"));
        movies.add(new Movie(5, "Movie 5", "Sci-Fi", 130, "PG-13", LocalTime.of(18, 0), "German"));
        movies.add(new Movie(6, "Movie 6", "Romance", 105, "PG", LocalTime.of(20, 0), "Italian"));

        // Save movies to the repository
        movieRepository.saveAll(movies);

    }
}