package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.UpdateTVShowUseCase;
import fontys.sem3.school.domain.UpdateTVShowRequest;
import fontys.sem3.school.persistence.TVShowRepository;
import fontys.sem3.school.persistence.entity.TVShowEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateTVShowUseCaseImpl implements UpdateTVShowUseCase {
    private final TVShowRepository tvShowRepository;

    @Override
    public void updateTVShow(UpdateTVShowRequest request) {
        tvShowRepository.save(TVShowEntity.builder().id(request.getId()).name(request.getName()).description(request.getDescription())
                .episodes(request.getEpisodes()).build());
    }
}
