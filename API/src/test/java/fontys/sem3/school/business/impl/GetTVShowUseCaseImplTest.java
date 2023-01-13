package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.TVShow;
import fontys.sem3.school.persistence.TVShowRepository;
import fontys.sem3.school.persistence.entity.TVShowEntity;
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
public class GetTVShowUseCaseImplTest {

    @Mock
    private TVShowRepository tvShowRepository;

    @InjectMocks
    private GetTVShowUseCaseImpl getTVShowUseCase;



    @Test
    public void testGetTVShow() {



        // Create a mock TVShow object
        TVShowEntity mockTVShow = new TVShowEntity(1, "Test Show", "This is a test show", 60);

        // Specify the behavior of the mock TVShowRepository
        when(tvShowRepository.findById(1L)).thenReturn(Optional.of(mockTVShow));

        // Call the getTVShow method
        Optional<TVShow> result = getTVShowUseCase.getTVShow(1L);

        // Assert that the correct TVShow object is returned
        assertTrue(result.isPresent());

        verify(tvShowRepository).findById(1L);
    }
}
