package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.UpdateMovieUseCase;
import fontys.sem3.school.domain.UpdateMovieRequest;
import fontys.sem3.school.persistence.MovieRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateMovieUseCaseImpl implements UpdateMovieUseCase {
    private final MovieRepository movieRepository;

    @Override
    public void updateMovie(UpdateMovieRequest request) {


        movieRepository.save(MovieEntity.builder().id(request.getId()).name(request.getName()).description(request.getDescription())
                .runtime(request.getRuntime()).build());
    }
}

