package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    boolean existsById(long movieId);

    Optional<MovieEntity> findById(long movieId);

    MovieEntity save(MovieEntity movie);

    void update(long id, String name, String description, int runtime);

    List<MovieEntity> findAll();

    int count();

    void deleteById(long movieId);
}
