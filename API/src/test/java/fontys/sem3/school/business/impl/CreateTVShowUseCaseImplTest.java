package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.CreateTVShowResponse;
import fontys.sem3.school.domain.CreateTVSowRequest;
import fontys.sem3.school.persistence.TVShowRepository;
import fontys.sem3.school.persistence.entity.TVShowEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTVShowUseCaseImplTest {

    @Mock
    private TVShowRepository tvShowRepository;
    @InjectMocks
    private CreateTVShowUseCaseImpl underTest;

    @Test
    public void testCreateTVShow() {

// create mock CreateTVSowRequest
        CreateTVSowRequest mockRequest = mock(CreateTVSowRequest.class);
// create mock TVShowEntity
        TVShowEntity mockTVShowEntity = mock(TVShowEntity.class);

// set up mockTVShowRepository to return mockTVShowEntity when save is called
        when(tvShowRepository.save(any(TVShowEntity.class))).thenReturn(mockTVShowEntity);
// set up mockTVShowEntity to return 1L when getId is called
        when(mockTVShowEntity.getId()).thenReturn(1L);

// call createTVShow method on useCase object with mockRequest
        CreateTVShowResponse response = underTest.createTVShow(mockRequest);

// verify that mockTVShowRepository's save method was called with a TVShowEntity object
        verify(tvShowRepository).save(any(TVShowEntity.class));
// assert that response's tvShowId is 1L
        assertEquals(response.getTvShowId(), 1L);
    }


}
