package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.GetTVShowsUseCase;
import fontys.sem3.school.domain.GetAllTVShowsResponse;
import fontys.sem3.school.domain.TVShow;
import fontys.sem3.school.persistence.TVShowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTVShowsUseCaseImpl implements GetTVShowsUseCase {

    private final TVShowRepository tvShowRepository;

    @Override
    public GetAllTVShowsResponse getTVShows() {
        List<TVShow> tvShowList = tvShowRepository.findAll()
                .stream()
                .map(TVShowConverter::convert)
                .toList();

        return GetAllTVShowsResponse.builder()
                .tvShows(tvShowList)
                .build();
    }
}
