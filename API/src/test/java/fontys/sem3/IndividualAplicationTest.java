package fontys.sem3;


import fontys.sem3.school.domain.Movie;
import fontys.sem3.school.domain.TVShow;
import fontys.sem3.school.domain.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class IndividualAplicationTest {
    private Object given;

    @Test
    public void testMovieBuilder()
    {
        Movie movie = Movie.builder().id(1).description("Description")
                .name("Name").runtime(130).build();
        assertEquals(movie.getId(),1);
        assertEquals(movie.getRuntime(), 130);
        assertEquals(movie.getName(), "Name");
        assertEquals(movie.getDescription(), "Description");
    }

    @Test
    public void TestTvShowBuilder(){
        TVShow tvShow = TVShow.builder().id(1).name("Name").description("Description")
                .episodes(15).build();
        assertEquals(tvShow.getId(), 1);
        assertEquals(tvShow.getName(), "Name");
        assertEquals(tvShow.getDescription(), "Description");
        assertEquals(tvShow.getEpisodes(), 15);
    }

    @Test
    public void TestTVShowSetterEpisodes(){
        TVShow tvShow = TVShow.builder().id(1).name("Name").description("Description")
                .episodes(15).build();
        tvShow.setEpisodes(25);
        assertEquals(tvShow.getEpisodes(), 25);
    }

    @Test
    public void TestMovieSetterRuntime(){
        Movie movie = Movie.builder().id(1).description("Description")
                .name("Name").runtime(130).build();
        movie.setRuntime(150);
        assertEquals(movie.getRuntime(), 150);
    }

    @Test
    public void TestingUser(){
        User user = User.builder().email("email").password("password").name("name").build();
        assertEquals(user.getName(), "name");
    }


}
