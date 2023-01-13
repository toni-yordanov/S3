/*
package fontys.sem3.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.school.business.*;
import fontys.sem3.school.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetMoviesUseCase getMoviesUseCase;

    @MockBean
    private DeleteMovieUseCase deleteMovieUseCase;

    @MockBean
    private GetMovieUseCase getMovieUseCase;

    @MockBean
    private CreateMovieUseCase createMovieUseCase;

    @MockBean
    private UpdateMovieUseCase updateMovieUseCase;

    @Test
    public void testGetMovies() throws Exception {
        // arrange

        GetAllMoviesResponse response = GetAllMoviesResponse.builder().movies(new ArrayList<>()).build();
        // Add some movies to the response
        when(getMoviesUseCase.getMovies()).thenReturn(response);

        // act and assert
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies", hasSize(0)));

        verify(getMoviesUseCase, times(1)).getMovies();
    }

    @Test
    public void testGetMovie() throws Exception {
        // arrange
        long id = 1;
        Optional<Movie> movie = Optional.of(new Movie());
        when(getMovieUseCase.getMovie(id)).thenReturn(movie);

        // act and assert
        mockMvc.perform(get("/movies/{id}", id))
                .andExpect(status().isOk());

        verify(getMovieUseCase, times(1)).getMovie(id);
    }

    @Test
    public void testGetMovie_NotFound() throws Exception {
        // arrange
        long id = 1;
        when(getMovieUseCase.getMovie(id)).thenReturn(Optional.empty());

        // act and assert
        mockMvc.perform(get("/movies/{id}", id))
                .andExpect(status().isNotFound());

        verify(getMovieUseCase, times(1)).getMovie(id);
    }

    @Test
    public void testDeleteMovie() throws Exception {
        // arrange
        int id = 1;
        doNothing().when(deleteMovieUseCase).deleteMovie(id);

        // act and assert
        mockMvc.perform(delete("/movies/{id}", id))
                .andExpect(status().isNoContent());

        verify(deleteMovieUseCase, times(1)).deleteMovie(id);
    }

    @Test
    public void testCreateMovie() throws Exception {
        // arrange
        CreateMovieRequest request = new CreateMovieRequest();
        request.setName("Test Movie");
        request.setDescription("Test Description");
        request.setRuntime(120);
        CreateMovieResponse response = CreateMovieResponse.builder().movieId(1).build();
        when(createMovieUseCase.createMovie(request)).thenReturn(response);

        // act and assert
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(createMovieUseCase, times(1)).createMovie(request);
    }

    @Test
    public void testUpdateMovie() throws Exception {
        // arrange
        long id = 1;
        UpdateMovieRequest request = new UpdateMovieRequest();
        request.setName("Test Movie Updated");
        request.setDescription("Test Description Updated");
        request.setRuntime(130);
        doNothing().when(updateMovieUseCase).updateMovie(request);

        // act and assert
        mockMvc.perform(put("/movies/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        verify(updateMovieUseCase, times(1)).updateMovie(request);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
*/
