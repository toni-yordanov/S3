package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.UpdateMovieRequest;
import fontys.sem3.school.persistence.MovieRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateMovieUseCaseImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private UpdateMovieUseCaseImpl updateMovieUseCase;

    @Test
    public void testUpdateMovie() {
        UpdateMovieRequest request = new UpdateMovieRequest(1L, "New Movie Name", "New Movie Description", 120);

        updateMovieUseCase.updateMovie(request);

        ArgumentCaptor<MovieEntity> captor = ArgumentCaptor.forClass(MovieEntity.class);
        verify(movieRepository).save(captor.capture());
        MovieEntity savedMovie = captor.getValue();


        assertEquals("New Movie Name", savedMovie.getName());
        assertEquals("New Movie Description", savedMovie.getDescription());
        assertEquals(120, savedMovie.getRuntime());
    }
    }

