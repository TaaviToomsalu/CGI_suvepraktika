package movieapp;

import movieapp.model.Movie;
import movieapp.repository.MovieRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
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
        List<Movie> movies = createMoviesForWeek();
        movieRepository.saveAll(movies);
    }

    private List<Movie> createMoviesForWeek() {
        List<Movie> movies = new ArrayList<>();
        // Define days of the week
        DayOfWeek[] daysOfWeek = DayOfWeek.values();

        // Original movie schedule
        LocalTime[] originalTimes = {
                LocalTime.of(10, 0), // 10:00 AM
                LocalTime.of(12, 0), // 12:00 PM
                LocalTime.of(14, 0), // 2:00 PM
                LocalTime.of(16, 0), // 4:00 PM
                LocalTime.of(18, 0), // 6:00 PM
                LocalTime.of(20, 0)  // 8:00 PM
        };

        // Create movies for each day of the week
        for (DayOfWeek dayOfWeek : daysOfWeek) {
            for (int i = 0; i < originalTimes.length; i++) {
                int movieId = (dayOfWeek.getValue() - 1) * originalTimes.length + (i + 1);
                movies.add(new Movie(
                        movieId, // Movie ID
                        "Movie " + (i + 1), // Movie title
                        getGenreForIndex(i), // Genre
                        getAgeRatingForIndex(i), // Age rating
                        originalTimes[i], // Start time
                        "Language " + (i + 1), // Language
                        dayOfWeek // Day of the week
                ));
            }
        }
        return movies;
    }

    // Helper methods to get genre, duration, and age rating based on index
    private String getGenreForIndex(int index) {
        // Example implementation
        String[] genres = {"Action", "Comedy", "Drama", "Thriller", "Sci-Fi", "Romance"};
        return genres[index % genres.length];
    }

    private String getAgeRatingForIndex(int index) {
        // Example implementation
        String[] ageRatings = {"PG-13", "PG", "G", "R", "PG-13", "NC-17"};
        return ageRatings[index % ageRatings.length];
    }
}