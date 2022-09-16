package fontys.sem3.school.persistence.impl;

import fontys.sem3.school.persistence.TVShowRepository;
import fontys.sem3.school.persistence.entity.MovieEntity;
import fontys.sem3.school.persistence.entity.TVShowEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FakeTVShowRepositoryImpl implements TVShowRepository{
    private static long NEXT_ID = 1;

    private final List<TVShowEntity> savedTVShows;

    public FakeTVShowRepositoryImpl() {
        this.savedTVShows = new ArrayList<>();
    }


    @Override
    public boolean existsById(long showId) {
        return this.savedTVShows
                .stream()
                .anyMatch(tvShowEntity -> tvShowEntity.getId() == showId);
    }

    @Override
    public Optional<TVShowEntity> findById(long showId) {
        return this.savedTVShows
                .stream()
                .filter(tvShowEntity -> tvShowEntity.getId() == showId)
                .findFirst();
    }

    @Override
    public TVShowEntity save(TVShowEntity tvShow) {
        tvShow.setId(NEXT_ID);
        NEXT_ID++;
        this.savedTVShows.add(tvShow);
        return tvShow;
    }

    @Override
    public void update(long id, String name, String description, int episodes) {
        TVShowEntity old = savedTVShows.stream().filter(show -> show.getId() == id).collect(Collectors.toList()).get(0);
        old.setEpisodes(episodes);
        old.setName(name);
        old.setDescription(description);
    }

    @Override
    public List<TVShowEntity> findAll() {
        return Collections.unmodifiableList(savedTVShows);
    }

    @Override
    public int count() {
        return this.savedTVShows.size();
    }



    @Override
    public void deleteById(long showId) {
        this.savedTVShows.removeIf(tvShowEntity -> tvShowEntity.getId().equals(showId));
    }
}
