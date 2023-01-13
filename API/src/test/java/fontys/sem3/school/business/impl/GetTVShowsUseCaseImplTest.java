package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.GetAllTVShowsResponse;
import fontys.sem3.school.domain.TVShow;
import fontys.sem3.school.persistence.TVShowRepository;
import fontys.sem3.school.persistence.entity.TVShowEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTVShowsUseCaseImplTest {
    @Mock
    private TVShowRepository repository;

    @InjectMocks
    private GetTVShowsUseCaseImpl getTVShowsUseCase;

    @Test
    void getTVShows() {
        TVShowEntity mockTvShow = TVShowEntity.builder().id(1L).description("Description 1").episodes(1).name("Name 1").build();
        TVShowEntity mockTvShow2 = TVShowEntity.builder().id(2L).description("Description 2").episodes(1).name("Name 2").build();

        when(repository.findAll()) .thenReturn(List.of(mockTvShow, mockTvShow2));
        GetAllTVShowsResponse actualResult = getTVShowsUseCase.getTVShows();

        TVShow  tvShow = TVShow.builder().id(1L).description("Description 1").episodes(1).name("Name 1").build();
        TVShow  tvShow2 = TVShow.builder().id(2L).description("Description 2").episodes(1).name("Name 2").build();

        GetAllTVShowsResponse expectedResult = GetAllTVShowsResponse .builder() .tvShows(List.of(tvShow, tvShow2)) .build();

        assertEquals(expectedResult, actualResult);
        verify(repository).findAll();
    }
}
