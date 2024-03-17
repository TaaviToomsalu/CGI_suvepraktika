package movieapp.controller;

import movieapp.model.UserAccount;
import movieapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserAccountRepository userRepository;

    @Autowired
    public UserController(UserAccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount user) {
        UserAccount newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to retrieve all users
    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        List<UserAccount> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/viewing-history/{movieId}")
    public ResponseEntity<UserAccount> addToViewingHistory(@PathVariable Long movieId, @RequestBody String userId) {
        try {
            // Convert userId String to Long
            Long userIdLong = Long.parseLong(userId);

            // Retrieve the user by ID
            Optional<UserAccount> userOptional = userRepository.findById(userIdLong);
            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Add the movieId to the user's viewing history
            UserAccount user = userOptional.get();
            Long[] viewingHistory = user.getViewingHistory();
            int length = viewingHistory.length;
            viewingHistory = Arrays.copyOf(viewingHistory, length + 1);
            viewingHistory[length] = movieId;
            user.setViewingHistory(viewingHistory);

            // Save the updated user object
            userRepository.save(user);

            return ResponseEntity.ok(user);
        } catch (NumberFormatException e) {
            // Handle invalid userId
            return ResponseEntity.badRequest().body("Invalid userId");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}