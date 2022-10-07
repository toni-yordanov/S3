package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.DeleteMovieUseCase;
import fontys.sem3.school.persistence.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteMovieImpl implements DeleteMovieUseCase {
    private  final MovieRepository movieRepository;

    @Override
    public void deleteMovie(long movieId) {
        this.movieRepository.deleteById(movieId);
    }
}
