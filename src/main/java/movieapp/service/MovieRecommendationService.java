package movieapp.service;

import movieapp.model.Movie;
import movieapp.model.UserAccount;
import movieapp.repository.MovieRepository;
import movieapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieRecommendationService {

    private final MovieRepository movieRepository;
    private final UserAccountRepository userRepository;

    @Autowired
    public MovieRecommendationService(MovieRepository movieRepository, UserAccountRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public List<Movie> getRecommendationsForUser(Long userId) {
        // Fetch user from repository
        UserAccount user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }

        // Retrieve user's viewing history and genres
        List<String> viewingHistory = user.getViewingHistory();
        List<String> viewingGenres = user.getViewingGenres();

        // Calculate the frequency of each genre in the user's viewing history
        Map<String, Integer> genreFrequencyMap = calculateGenreFrequency(viewingGenres);

        // Find the most watched genre
        String mostWatchedGenre = findMostWatchedGenre(genreFrequencyMap);

        // Fetch movies of the most watched genre from the repository
        List<Movie> recommendedMovies = movieRepository.findByGenre(mostWatchedGenre);

        // Return recommended movies
        return recommendedMovies;
    }

    // Helper method to calculate the frequency of each genre in the user's viewing history
    private Map<String, Integer> calculateGenreFrequency(List<String> viewingGenres) {
        Map<String, Integer> genreFrequencyMap = new HashMap<>();
        for (String genre : viewingGenres) {
            genreFrequencyMap.put(genre, genreFrequencyMap.getOrDefault(genre, 0) + 1);
        }
        return genreFrequencyMap;
    }

    // Helper method to find the most watched genre
    private String findMostWatchedGenre(Map<String, Integer> genreFrequencyMap) {
        return Collections.max(genreFrequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

}
