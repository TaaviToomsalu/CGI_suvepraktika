package movieapp.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Table(name= "USERS")
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long[] viewingHistory;

    // Default constructor
    public UserAccount() {
    }

    //Constructor
    public UserAccount(Long id, String username, Long[] viewingHistory) {
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

    public Long[] getViewingHistory() {
        return viewingHistory;
    }

    public void setViewingHistory(Long[] viewingHistory) {
        this.viewingHistory = viewingHistory;
    }


    // Override toString method
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", viewingHistory=" + Arrays.toString(viewingHistory) +
                '}';
    }

}

