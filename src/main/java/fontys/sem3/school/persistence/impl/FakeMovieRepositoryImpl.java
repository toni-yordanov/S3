package fontys.sem3.school.persistence.impl;

import fontys.sem3.school.persistence.MovieRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FakeMovieRepositoryImpl implements MovieRepository {
    private static long NEXT_ID = 1;

    private final List<MovieEntity> savedMovies;

    public FakeMovieRepositoryImpl() {
        this.savedMovies = new ArrayList<>();
    }


    @Override
    public boolean existsById(long movieId) {
        return this.savedMovies
                .stream()
                .anyMatch(movieEntity -> movieEntity.getId() == movieId);
    }

    @Override
    public Optional<MovieEntity> findById(long movieId) {
        return this.savedMovies.stream()
                .filter(movieEntity -> movieEntity.getId() == movieId)
                .findFirst();
    }

    @Override
    public MovieEntity save(MovieEntity movie) {
        movie.setId(NEXT_ID);
        NEXT_ID++;
        this.savedMovies.add(movie);
        return movie;
    }

    @Override
    public void update(long id, String name, String description, int runtime) {
        MovieEntity oldMovie = savedMovies.stream().filter(MovieEntity -> MovieEntity.getId() == id).collect(Collectors.toList()).get(0);
        oldMovie.setDescription(description);
        oldMovie.setName(name);
        oldMovie.setRuntime(runtime);
    }

    @Override
    public List<MovieEntity> findAll() {
        return Collections.unmodifiableList(savedMovies);
    }

    @Override
    public int count() {
        return this.savedMovies.size();
    }



    @Override
    public void deleteById(long movieId) {
        this.savedMovies.removeIf(MovieEntity -> MovieEntity.getId().equals(movieId));
    }
}
