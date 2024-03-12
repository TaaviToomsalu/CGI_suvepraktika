package movieapp.repository;

import java.time.LocalTime;
import java.util.List;
import movieapp.model.Movie;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository {
    List<Movie> findAll();
    Movie findById(Long id);
    List<Movie> findByGenre(String genre);
    List<Movie> findByAgeRating(String ageRating);
    List<Movie> findByStartTime(LocalTime startTime);
    List<Movie> findByLanguage(String language);

    void addAll(List<Movie> movies);
    /*
    void save(Movie movie);
    void update(Movie movie);
    void delete(Long id);
     */
}