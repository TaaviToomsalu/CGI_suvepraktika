package movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import movieapp.model.Movie;
import movieapp.repository.MovieRepository;

import java.time.LocalTime;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello, world!");
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
    public ResponseEntity<List<Movie>> getMoviesByStartTime(@RequestParam String startTime) {
        LocalTime startTimeParsed = LocalTime.parse(startTime);
        List<Movie> movies = movieRepository.findByStartTime(startTimeParsed);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/age-rating")
    public ResponseEntity<List<Movie>> getMoviesByAgeRating(@RequestParam String ageRating) {
        List<Movie> movies = movieRepository.findByAgeRating(ageRating);
        return ResponseEntity.ok(movies);
    }
}