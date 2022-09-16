package fontys.sem3.school.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllTVShowsResponse {
    private List<TVShow> tvShows;
}
