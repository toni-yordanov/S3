package fontys.sem3.school.business;

import fontys.sem3.school.domain.TVShow;

import java.util.Optional;

public interface GetTVShowUseCase {
    Optional<TVShow> getTVShow(long showId);
}
