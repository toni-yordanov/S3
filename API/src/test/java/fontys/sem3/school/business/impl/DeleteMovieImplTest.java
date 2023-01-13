package fontys.sem3.school.business.impl;

import fontys.sem3.school.persistence.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteMovieImplTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private DeleteMovieImpl deleteMovieUseCase;

    @Test
    void testDeleteMovie() {
        // Call the deleteMovie method
        deleteMovieUseCase.deleteMovie(1L);

        // Verify that the deleteById method of the mock MovieRepository is called
        verify(movieRepository).deleteById(1L);
    }
}
