package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.entity.MovieEntity;
import fontys.sem3.school.persistence.entity.TVShowEntity;

import java.util.List;
import java.util.Optional;

public interface TVShowRepository {
    boolean existsById(long showId);

    Optional<TVShowEntity> findById(long showId);


    TVShowEntity save(TVShowEntity tvShow);

    void update(long id, String name, String description, int episodes);

    List<TVShowEntity> findAll();

    int count();

    void deleteById(long showId);
}
