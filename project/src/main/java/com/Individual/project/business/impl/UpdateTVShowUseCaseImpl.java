package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.UpdateTVShowUseCase;
import fontys.sem3.school.domain.UpdateTVShowRequest;
import fontys.sem3.school.persistence.TVShowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateTVShowUseCaseImpl implements UpdateTVShowUseCase {
    private final TVShowRepository tvShowRepository;
    @Override
    public void updateTVShow(UpdateTVShowRequest request) {
        tvShowRepository.update(request.getId(), request.getName(), request.getDescription(), request.getEpisodes());
    }
}
