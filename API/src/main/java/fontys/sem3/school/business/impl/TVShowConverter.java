package fontys.sem3.school.business.impl;

import fontys.sem3.school.domain.TVShow;
import fontys.sem3.school.persistence.entity.TVShowEntity;

public class TVShowConverter {
    private TVShowConverter(){

    }
    public static TVShow convert(TVShowEntity tvShow){
        return TVShow.builder()
                .id(tvShow.getId())
                .name(tvShow.getName())
                .description(tvShow.getDescription())
                .episodes(tvShow.getEpisodes())
                .build();
    }
}
