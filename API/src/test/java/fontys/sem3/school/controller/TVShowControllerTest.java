package fontys.sem3.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.school.business.*;
import fontys.sem3.school.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TVShowControllerTest {

    @Mock
    private GetTVShowsUseCase getTVShowsUseCase;
    @Mock
    private DeleteTVShowUseCase deleteTVShowUseCase;
    @Mock
    private GetTVShowUseCase getTVShowUseCase;
    @Mock
    private CreateTVShowUseCase createTVShowUseCase;
    @Mock
    private UpdateTVShowUseCase updateTVShowUseCase;

    @InjectMocks
    private TVShowController tvShowController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tvShowController).build();
    }

    @Test
    public void testGetTVShows() throws Exception {
        // arrange
        GetAllTVShowsResponse response =  GetAllTVShowsResponse.builder().build();
        when(getTVShowsUseCase.getTVShows()).thenReturn(response);

        // act and assert
        mockMvc.perform(get("/tvshows"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(response)));

        verify(getTVShowsUseCase, times(1)).getTVShows();
    }

    @Test
    public void testGetTVShow_whenShowExists() throws Exception {
        // arrange
        long id = 1L;
        TVShow tvShow = new TVShow();
        when(getTVShowUseCase.getTVShow(id)).thenReturn(Optional.of(tvShow));

        // act and assert
        mockMvc.perform(get("/tvshows/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(tvShow)));

        verify(getTVShowUseCase, times(1)).getTVShow(id);
    }

    @Test
    public void testGetTVShow_whenShowDoesNotExist() throws Exception {
// arrange
        long id = 1L;
        when(getTVShowUseCase.getTVShow(id)).thenReturn(Optional.empty());
        // act and assert
        mockMvc.perform(get("/tvshows/{id}", id))
                .andExpect(status().isNotFound());

        verify(getTVShowUseCase, times(1)).getTVShow(id);
    }

    @Test
    public void testDeleteTVShow() throws Exception {
        // arrange
        int id = 1;

        // act and assert
        mockMvc.perform(delete("/tvshows/{id}", id))
                .andExpect(status().isNoContent());

        verify(deleteTVShowUseCase, times(1)).deleteTVShow(id);
    }


    @Test
    public void testGetTVShow_whenShowNotExists() throws Exception {
// arrange
        long id = 1L;
        when(getTVShowUseCase.getTVShow(id)).thenReturn(Optional.empty());
    // act and assert
    mockMvc.perform(get("/tvshows/{id}", id))
            .andExpect(status().isNotFound());

    verify(getTVShowUseCase, times(1)).getTVShow(id);
}

    @Test
    public void testDeleteTVShow1() throws Exception {
        // arrange
        int showId = 1;

        // act and assert
        mockMvc.perform(delete("/tvshows/{showId}", showId))
                .andExpect(status().isNoContent());

        verify(deleteTVShowUseCase, times(1)).deleteTVShow(showId);
    }

    @Test
    public void testCreateTVShow1() throws Exception {
        // arrange
        CreateTVSowRequest request = new CreateTVSowRequest();
        request.setName("name");
        request.setEpisodes(1);
        request.setDescription("Des");
        CreateTVShowResponse response = CreateTVShowResponse.builder().build();
        when(createTVShowUseCase.createTVShow(request)).thenReturn(response);
        ObjectMapper objectMapper = new ObjectMapper();

        String requestJson = objectMapper.writeValueAsString(request);

        // act and assert
        mockMvc.perform(post("/tvshows/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(response)));

        verify(createTVShowUseCase, times(1)).createTVShow(request);
    }

    @Test
    public void testUpdateTVShow1() throws Exception {
        // arrange
        long id = 1L;
        UpdateTVShowRequest request = new UpdateTVShowRequest();
        request.setId(id);
        request.setName("name");
        request.setEpisodes(1);
        request.setDescription("Des");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        // act and assert
        mockMvc.perform(put("/tvshows/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(updateTVShowUseCase, times(1)).updateTVShow(request);
    }


}