package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.GetTVShowUseCase;
import fontys.sem3.school.domain.TVShow;
import fontys.sem3.school.persistence.TVShowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetTVShowUseCaseImpl implements GetTVShowUseCase {
    private TVShowRepository tvShowRepository;

    @Override
    public Optional<TVShow> getTVShow(long showId) {
        return tvShowRepository.findById(showId).map(TVShowConverter::convert);
    }
}
