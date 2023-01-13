package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.UpdateTVShowRequest;
import fontys.sem3.school.persistence.TVShowRepository;
import fontys.sem3.school.persistence.entity.TVShowEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateTVShowUseCaseImplTest {
    @Mock
    private TVShowRepository tvShowRepository;

    @InjectMocks
    private UpdateTVShowUseCaseImpl updateTVShowUseCase;

    @Test
    public void testUpdateTVShow() {
        UpdateTVShowRequest request = new UpdateTVShowRequest(1L, "New Show Name", "New Show Description", 120);

        updateTVShowUseCase.updateTVShow(request);

        ArgumentCaptor<TVShowEntity> captor = ArgumentCaptor.forClass(TVShowEntity.class);
        verify(tvShowRepository).save(captor.capture());
        TVShowEntity savedTvShow = captor.getValue();


        assertEquals("New Show Name", savedTvShow.getName());
        assertEquals("New Show Description", savedTvShow.getDescription());
        assertEquals(120, savedTvShow.getEpisodes());


    }
}
