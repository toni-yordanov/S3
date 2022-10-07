package fontys.sem3.school.business;

import fontys.sem3.school.domain.CreateMovieRequest;
import fontys.sem3.school.domain.CreateMovieResponse;

public interface CreateMovieUseCase {
    CreateMovieResponse createMovie(CreateMovieRequest request);
}
