package fontys.sem3.school.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllMoviesResponse {
    private List<Movie> movies;
}
