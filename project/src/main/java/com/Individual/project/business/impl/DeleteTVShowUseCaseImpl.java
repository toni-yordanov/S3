package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.DeleteTVShowUseCase;
import fontys.sem3.school.persistence.TVShowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTVShowUseCaseImpl implements DeleteTVShowUseCase {

    private final TVShowRepository tvShowRepository;


    @Override
    public void deleteTVShow(long showId) {
        this.tvShowRepository.deleteById(showId);
    }
}
