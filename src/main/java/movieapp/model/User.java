package movieapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @OneToMany
    private List<Movie> viewingHistory;

    // Default constructor
    public User() {
    }

    //Constructor
    public User(Long id, String username, List<Movie> viewingHistory) {
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

    public List<Movie> getViewingHistory() {
        return viewingHistory;
    }

    public void setViewingHistory(List<Movie> viewingHistory) {
        this.viewingHistory = viewingHistory;
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

