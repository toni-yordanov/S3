package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.Movie;
import fontys.sem3.school.persistence.MovieRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetMovieUseCaseImplTest {



    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private GetMovieUseCaseImpl movieUseCase;

    @Test
    void getMovie() {





        // Create a mock Movie object
        MovieEntity mockMovie = new MovieEntity(1, "Test Movie", "This is a test Movie", 60);

        // Specify the behavior of the mock MovieRepository
        when(movieRepository.findById(1L)).thenReturn(Optional.of(mockMovie));

        // Call the getMovie method
        Optional<Movie> result = movieUseCase.getMovie(1L);

        // Assert that the correct Movie object is returned
        assertTrue(result.isPresent());

        verify(movieRepository).findById(1L);
    }
}