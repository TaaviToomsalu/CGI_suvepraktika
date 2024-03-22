package movieapp.controller;

import movieapp.model.UserAccount;
import movieapp.repository.UserAccountRepository;
import movieapp.service.MovieRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import movieapp.model.Movie;
import movieapp.repository.MovieRepository;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private MovieRecommendationService recommendationService;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Back works!");
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/genre")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre) {
        List<Movie> movies = movieRepository.findByGenre(genre);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/language")
    public ResponseEntity<List<Movie>> getMoviesByLanguage(@RequestParam String language) {
        List<Movie> movies = movieRepository.findByLanguageIgnoreCase(language);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/start-time")
    public ResponseEntity<List<Movie>> getMoviesStartingAfterTime(@RequestParam String startTime) {
        try {
            LocalTime startTimeParsed = LocalTime.parse(startTime);
            List<Movie> movies = movieRepository.findByStartTimeAfterOrStartTimeEquals(startTimeParsed, startTimeParsed);
            return ResponseEntity.ok(movies);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    @GetMapping("/movies/age-rating")
    public ResponseEntity<List<Movie>> getMoviesByAgeRating(@RequestParam String ageRating) {
        List<Movie> movies = movieRepository.findByAgeRating(ageRating);
        return ResponseEntity.ok(movies);
    }


    @GetMapping("/movies/recommendations")
    public ResponseEntity<List<Movie>> getRecommendationsForUser(@RequestParam Long userId) {
        UserAccount user = userAccountRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Movie> recommendations = recommendationService.getRecommendationsForUser(userId);
        return ResponseEntity.ok(recommendations);
    }
}