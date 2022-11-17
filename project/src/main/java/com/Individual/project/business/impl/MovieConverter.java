package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.Movie;
import fontys.sem3.school.persistence.entity.MovieEntity;

final class MovieConverter {
    private MovieConverter(){

    }

    public static Movie convert(MovieEntity movie){
        return Movie.builder()
                .id(movie.getId())
                .name(movie.getName())
                .description(movie.getDescription())
                .runtime(movie.getRuntime())
                .build();
    }
}
