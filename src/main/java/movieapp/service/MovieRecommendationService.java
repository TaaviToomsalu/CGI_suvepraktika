package movieapp.service;

import movieapp.model.Movie;
import movieapp.model.UserAccount;
import movieapp.repository.MovieRepository;
import movieapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRecommendationService {

    private final MovieRepository movieRepository;
    private final UserAccountRepository userRepository;

    @Autowired
    public MovieRecommendationService(MovieRepository movieRepository, UserAccountRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    // Example method to get recommendations for a user
    public List<Movie> getRecommendationsForUser(Long userId) {
        // Fetch user from repository
        UserAccount user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }

        // Implement your recommendation logic here based on user's viewing history, preferences, etc.
        // For now, let's just return all movies
        return movieRepository.findAll();
    }

    // Example method to update user's viewing history
    public UserAccount updateUserViewingHistory(Long userId, Long[] viewingHistory) {
        UserAccount user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setViewingHistory(viewingHistory);
            return userRepository.save(user);
        }
        return null;
    }

    // Add more methods as needed...
}