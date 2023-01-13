package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.CreateMovieRequest;
import fontys.sem3.school.domain.CreateMovieResponse;
import fontys.sem3.school.persistence.MovieRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateMovieUseCaseImplTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private CreateMovieUseCaseImpl createMovieUseCase;

    @Test
    void testCreateMovie() {
        // Create a mock CreateMovieRequest object
        CreateMovieRequest request = new CreateMovieRequest("Test Movie", "This is a test movie", 120);
        // Create a mock MovieEntity object
        MovieEntity movieEntity = new MovieEntity(1, "Test Movie", "This is a test movie", 120);

        // Specify the behavior of the mock MovieRepository object
        when(movieRepository.save(any(MovieEntity.class))).thenReturn(movieEntity);

        // Call the createMovie method
        CreateMovieResponse response = createMovieUseCase.createMovie(request);

        // Verify that the save method of the mock MovieRepository is called with the expected MovieEntity object
        verify(movieRepository).save(any(MovieEntity.class));

        // Assert that the correct values are returned in the CreateMovieResponse object
        assertEquals(1, response.getMovieId());
    }
}
