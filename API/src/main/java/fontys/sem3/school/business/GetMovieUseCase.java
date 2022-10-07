package fontys.sem3.school.business;

import fontys.sem3.school.domain.Movie;

import java.util.Optional;

public interface GetMovieUseCase {
    Optional<Movie> getMovie(long movieId);
}
