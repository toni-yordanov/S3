package fontys.sem3.school.business;

import fontys.sem3.school.domain.CreateTVShowResponse;
import fontys.sem3.school.domain.CreateTVSowRequest;

public interface CreateTVShowUseCase {
    CreateTVShowResponse createTVShow(CreateTVSowRequest request);
}
