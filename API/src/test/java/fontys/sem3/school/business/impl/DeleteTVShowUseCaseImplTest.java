package fontys.sem3.school.business.impl;

import fontys.sem3.school.persistence.TVShowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class DeleteTVShowUseCaseImplTest {


    @Mock
    private TVShowRepository tvShowRepository;

    @InjectMocks
    private DeleteTVShowUseCaseImpl deleteTVShowUseCase;

    @Test
    void deleteTVShow() {
        // Call the deleteMovie method
        deleteTVShowUseCase.deleteTVShow(1L);

        // Verify that the deleteById method of the mock MovieRepository is called
        verify(tvShowRepository).deleteById(1L);
    }
}