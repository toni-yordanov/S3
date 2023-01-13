package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.GetAllMoviesResponse;
import fontys.sem3.school.domain.Movie;
import fontys.sem3.school.persistence.MovieRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
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
class GetMoviesUseCaseImplTest {

    @Mock
    private MovieRepository repository;

    @InjectMocks
    private GetMoviesUseCaseImpl getMoviesUseCase;

    @Test
    void getMovies() {
        MovieEntity mockMovie = MovieEntity.builder().id(1L).description("Description 1").runtime(1).name("Name 1").build();
        MovieEntity mockMovie2 = MovieEntity.builder().id(2L).description("Description 2").runtime(2).name("Name 2").build();

        when(repository.findAll()) .thenReturn(List.of(mockMovie, mockMovie2));
        GetAllMoviesResponse actualResult = getMoviesUseCase.getMovies();

        Movie movie = Movie.builder().id(1L).description("Description 1").runtime(1).name("Name 1").build();
        Movie movie2 = Movie.builder().id(2L).description("Description 2").runtime(2).name("Name 2").build();

        GetAllMoviesResponse expectedResult = GetAllMoviesResponse .builder() .movies(List.of(movie, movie2)) .build();

        assertEquals(expectedResult, actualResult);
        verify(repository).findAll();
    }
}
