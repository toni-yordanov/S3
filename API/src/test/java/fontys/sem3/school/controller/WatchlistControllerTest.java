/*
package fontys.sem3.school.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.school.business.UserService;
import fontys.sem3.school.business.WatchlistService;
import fontys.sem3.school.persistence.entity.UserEntity;
import fontys.sem3.school.persistence.entity.WatchlistEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class WatchlistControllerTest {

    @Mock
    private WatchlistService watchlistService;

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private WatchlistController watchlistController;

    private MockMvc mockMvc;

    @Test
    public void testGetWatchlists() throws Exception {
        // arrange
        List<WatchlistEntity> watchlists = new ArrayList<>();
        when(watchlistService.getWatchlists()).thenReturn(watchlists);

        mockMvc = MockMvcBuilders.standaloneSetup(watchlistController).build();

        // act and assert
        mockMvc.perform(get("/watchlist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(watchlistService, times(1)).getWatchlists();
    }

    @Test
    public void testGetWatchlist() throws Exception {
        // arrange
        long id = 1;
        List<WatchlistEntity> watchlists = new ArrayList<>();
        WatchlistEntity watchlist = new WatchlistEntity();
        watchlist.setId(id);
        watchlists.add(watchlist);

        when(watchlistService.getWatchlists()).thenReturn(watchlists);
        mockMvc = MockMvcBuilders.standaloneSetup(watchlistController).build();

        // act and assert
        mockMvc.perform(get("/watchlist/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo((int) id)));

        verify(watchlistService, times(1)).getWatchlists();
    }

    @Test
    @WithMockUser(username = "Nane", password = "1234",roles = {"ROLL_USER"})
    public void testGetWatchlistForUser() throws Exception {
        // arrange
        String currentPrincipalName = "user@example.com";
        UserEntity loggedInUser = new UserEntity();
        loggedInUser.setId(1L);
        when(authentication.getName()).thenReturn(currentPrincipalName);
        when(userService.getUser(currentPrincipalName)).thenReturn(loggedInUser);
        List<WatchlistEntity> watchlists = new ArrayList<>();
        when(watchlistService.getWatchlistForUser(loggedInUser.getId())).thenReturn(watchlists);
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String token = JWT.create()
                .withSubject("user@example.com")
                .withExpiresAt(new Date(System.currentTimeMillis() + 100*60*1000))
                .withIssuer("issuer")
                .sign(algorithm);

        mockMvc = MockMvcBuilders.standaloneSetup(watchlistController).build();

        // act and assert
        mockMvc.perform(get("/watchlist/personal")
                .header("Authorization", "Bearer " + token))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(userService, times(1)).getUser(currentPrincipalName);
        verify(watchlistService, times(1)).getWatchlistForUser(loggedInUser.getId());
    }





    @Test
    public void testSaveWatchlist() throws Exception {
        // arrange
        WatchlistEntity watchlist = new WatchlistEntity();
        UserEntity user = new UserEntity();
        user.setId(1L);
        userService.saveUser(user);
        watchlist.setUserId(user.getId());

        // mock the authentication
        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
        when(auth.getName()).thenReturn("name@mail");
        when(userService.getUser("name@mail")).thenReturn(user);
        when(watchlistService.saveWatchlist(watchlist)).thenReturn(watchlist);

        ObjectMapper objectMapper = new ObjectMapper();
        String watchlistJson = objectMapper.writeValueAsString(watchlist);

        mockMvc = MockMvcBuilders.standaloneSetup(watchlistController).build();

        // act and assert
        mockMvc.perform(post("/watchlist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(watchlistJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(watchlistService, times(1)).saveWatchlist(watchlist);
    }






    @Test
    public void testUpdateWatchlist() throws Exception {
        // arrange
        long id = 1L;
        WatchlistEntity watchlist = new WatchlistEntity();
        watchlist.setId(id);
        when(watchlistService.updateWatchlist(id, watchlist)).thenReturn(watchlist);

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String token = JWT.create()
                .withSubject("name@mail")
                .withExpiresAt(new Date(System.currentTimeMillis() + 100*60*1000))
                .withIssuer("issuer")
                .sign(algorithm);

        mockMvc = MockMvcBuilders.standaloneSetup(watchlistController).build();

        // act and assert
        mockMvc.perform(put("/watchlist/{watchlistId}", id)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(watchlist)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo((int) id)));

        verify(watchlistService, times(1)).updateWatchlist(id, watchlist);
    }

    @Test
    public void testDeleteWatchlist() throws Exception {
        // arrange
        long id = 1;

        mockMvc = MockMvcBuilders.standaloneSetup(watchlistController).build();

        // act and assert
        mockMvc.perform(delete("/watchlist/{watchlistId}", id))
                .andExpect(status().isNoContent());

        verify(watchlistService, times(1)).deleteWatchlist(id);
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
