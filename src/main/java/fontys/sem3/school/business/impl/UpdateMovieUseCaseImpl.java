package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.UpdateMovieUseCase;
import fontys.sem3.school.domain.UpdateMovieRequest;
import fontys.sem3.school.persistence.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateMovieUseCaseImpl implements UpdateMovieUseCase {
    private final MovieRepository movieRepository;

    @Override
    public void updateMovie(UpdateMovieRequest request) {

        movieRepository.update(request.getId(), request.getName(), request.getDescription(), request.getRuntime());
    }
}

