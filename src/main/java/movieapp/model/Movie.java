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
    private String ageRating;
    private LocalTime startTime;
    private String language;
    private DayOfWeek dayOfWeek;

    //Default constructor
    public Movie() {
    }

    //Constructor
    public Movie(int id, String title, String genre, String ageRating, LocalTime startTime, String language, DayOfWeek dayOfWeek) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.ageRating = ageRating;
        this.startTime = startTime;
        this.language = language;
        this.dayOfWeek = dayOfWeek;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public String getLanguage() {
        return language;
    }

    // Getter and setter for dayOfWeek
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }


    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", ageRating='" + ageRating + '\'' +
                ", startTime=" + startTime +
                ", language='" + language + '\'' +
                '}';
    }
}
