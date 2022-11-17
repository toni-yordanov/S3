package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.CreateTVShowUseCase;
import fontys.sem3.school.domain.*;
import fontys.sem3.school.persistence.TVShowRepository;
import fontys.sem3.school.persistence.entity.TVShowEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTVShowUseCaseImpl implements CreateTVShowUseCase {
    private final TVShowRepository tvShowRepository;


    @Override
    public CreateTVShowResponse createTVShow(CreateTVSowRequest request) {

        TVShowEntity savedTVShow = saveNewTVShow(request);
        return CreateTVShowResponse.builder()
                .tvShowId(savedTVShow.getId())
                .build();
    }

    private TVShowEntity saveNewTVShow(CreateTVSowRequest request){

        TVShowEntity newShow = TVShowEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .episodes(request.getEpisodes())
                .build();
        return tvShowRepository.save(newShow);
    }
}
