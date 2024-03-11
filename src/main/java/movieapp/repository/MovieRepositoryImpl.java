package movieapp.repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import movieapp.model.Movie;

public class MovieRepositoryImpl implements MovieRepository {

    private final List<Movie> movies = new ArrayList<>();

    @Override
    public List<Movie> findAll() {
        return movies;
    }

    @Override
    public Movie findById(Long id) {
        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                result.add(movie);
            }
        }
        return result;
    }

    @Override
    public List<Movie> findByAgeRating(String ageRating) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (compareAgeRating(movie.getAgeRating(), ageRating) <= 0) {
                result.add(movie);
            }
        }
        return result;
    }

    @Override
    public List<Movie> findByStartTime(LocalTime startTime) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getStartTime().isAfter(startTime)) {
                result.add(movie);
            }
        }
        return result;
    }

    @Override
    public List<Movie> findByLanguage(String language) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getLanguage().equalsIgnoreCase(language)) {
                result.add(movie);
            }
        }
        return result;
    }

    // Implement other methods similarly

    /*
    @Override
    public void save(Movie movie) {
        movies.add(movie);
    }


    @Override
    public void update(Movie movie) {
        // Implement update logic
    }

    @Override
    public void delete(Long id) {
        // Implement delete logic
    }
    */

    private int compareAgeRating(String rating1, String rating2) {
        // Assign numerical values to each age rating
        int value1 = getAgeRatingValue(rating1);
        int value2 = getAgeRatingValue(rating2);

        // Compare the numerical values
        return Integer.compare(value1, value2);
    }

    private int getAgeRatingValue(String rating) {
        return switch (rating) {
            case "G" -> 1;
            case "PG" -> 2;
            case "PG-13" -> 3;
            case "R" -> 4;
            case "NC-17" -> 5;
            default -> 0; // Unknown rating
        };
    }

    @Override
    public void addAll(List<Movie> movies) {
        this.movies.addAll(movies);
    }
}