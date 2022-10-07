package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.CreateMovieUseCase;
import fontys.sem3.school.domain.CreateMovieRequest;
import fontys.sem3.school.domain.CreateMovieResponse;
import fontys.sem3.school.persistence.MovieRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateMovieUseCaseImpl implements CreateMovieUseCase {
    private final MovieRepository movieRepository;


    @Override
    public CreateMovieResponse createMovie(CreateMovieRequest request) {

        MovieEntity savedMovie = saveNewMovie(request);
        return CreateMovieResponse.builder()
                .movieId(savedMovie.getId())
                .build();
    }

    private MovieEntity saveNewMovie(CreateMovieRequest request){

        MovieEntity newMovie = MovieEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .runtime(request.getRuntime())
                .build();
        return movieRepository.save(newMovie);
    }
}
