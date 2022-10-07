package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.GetMovieUseCase;
import fontys.sem3.school.domain.Movie;
import fontys.sem3.school.persistence.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetMovieUseCaseImpl implements GetMovieUseCase {
    private MovieRepository movieRepository;

    @Override
    public Optional<Movie> getMovie(long movieId) {
        return movieRepository.findById(movieId).map(MovieConverter::convert);
    }
}
