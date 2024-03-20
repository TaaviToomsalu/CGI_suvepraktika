package movieapp.model;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Table(name= "MOVIES")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String genre;
    private int duration;
    private String ageRating;
    private LocalTime startTime;
    private String language;

    //Default constructor
    public Movie() {
    }

    //Constructor
    public Movie(int id, String title, String genre, int duration, String ageRating, LocalTime startTime, String language) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.ageRating = ageRating;
        this.startTime = startTime;
        this.language = language;
    }

    //Getters

    public String getGenre() {
        return genre;
    }


    // Setters
    // Override toString method
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    public String getAgeRating() {
        return ageRating;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

}

