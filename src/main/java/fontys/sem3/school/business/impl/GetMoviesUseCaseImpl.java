package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.GetMoviesUseCase;
import fontys.sem3.school.domain.GetAllMoviesResponse;
import fontys.sem3.school.domain.Movie;
import fontys.sem3.school.persistence.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetMoviesUseCaseImpl implements GetMoviesUseCase {
    private final MovieRepository movieRepository;

    @Override
    public GetAllMoviesResponse getMovies() {
        List<Movie> movieList = movieRepository.findAll()
                .stream()
                .map(MovieConverter::convert)
                .toList();

        return GetAllMoviesResponse.builder()
                .movies(movieList).build();
    }
}
