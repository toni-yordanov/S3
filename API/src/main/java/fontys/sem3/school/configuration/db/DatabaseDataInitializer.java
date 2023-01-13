/*
package fontys.sem3.school.configuration.db;

import fontys.sem3.school.business.UserService;
import fontys.sem3.school.persistence.MovieRepository;
import fontys.sem3.school.persistence.TVShowRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
import fontys.sem3.school.persistence.entity.RoleEntity;
import fontys.sem3.school.persistence.entity.TVShowEntity;
import fontys.sem3.school.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {

    private MovieRepository movieRepository;
    private TVShowRepository tvShowRepository;
    private UserService userService;

    */
/*@EventListener(ApplicationReadyEvent.class)
    public void populateMovieDatabaseInitialDummyData() {
        if (movieRepository.count() == 0) {
            movieRepository.save(MovieEntity.builder().name("Avengers").description("Superhero movie").runtime(190).build());
            movieRepository.save(MovieEntity.builder().name("Harry Potter").description("Magic movie").runtime(150).build());
            movieRepository.save(MovieEntity.builder().name("Lord of the rings").description("Supernatural movie").runtime(300).build());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateTVDatabaseInitialDummyData() {
        if (tvShowRepository.count() == 0) {
            tvShowRepository.save(TVShowEntity.builder().name("Breaking Bad Season 1").description("Chemestry tv show").episodes(25).build());
            tvShowRepository.save(TVShowEntity.builder().name("X-man 97").description("Superhero tv show").episodes(52).build());
            tvShowRepository.save(TVShowEntity.builder().name("All inclusive").description("Comedy tv show").episodes(12).build());
        }
    }*//*


    */
/*@EventListener(ApplicationReadyEvent.class)
    public void populateUserDummyData() {

        userService.saveRole(new RoleEntity(null,"ROLL_USER"));
        userService.saveRole(new RoleEntity(null,"ROLL_ADMIN"));

        userService.saveUser(new UserEntity(null, "Anton", "anton@mail", "1234", new ArrayList<>()));
        userService.saveUser(new UserEntity(null, "Alex", "alex@mail", "1234", new ArrayList<>()));

        userService.addRoleToUser("anton@mail", "ROLL_ADMIN");
        userService.addRoleToUser("alex@mail", "ROLL_USER");
        }
    }*//*


*/
