package movieapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Table(name= "USERS")
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @ElementCollection
    @CollectionTable(name = "user_viewing_history", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "movie_title")
    private List<String> viewingHistory = new ArrayList<>();

    // Define a new field to store the genres of viewed movies
    @ElementCollection
    @CollectionTable(name = "user_viewing_genre", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "movie_genre")
    private List<String> viewingGenres = new ArrayList<>();

    // Default constructor
    public UserAccount() {
    }

    //Constructor
    public UserAccount(Long id, String username, List<String> viewingHistory) {
        this.id = id;
        this.username = username;
        this.viewingHistory = viewingHistory;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getViewingHistory() {
        return viewingHistory;
    }

    public void setViewingHistory(List<String> viewingHistory) {
        this.viewingHistory = viewingHistory;
    }

    // Getter for viewingGenres
    public List<String> getViewingGenres() {
        return viewingGenres;
    }

    // Setter for viewingGenres
    public void setViewingGenres(List<String> viewingGenres) {
        this.viewingGenres = viewingGenres;
    }


    // Override toString method
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", viewingHistory=" + viewingHistory +
                '}';
    }

}

