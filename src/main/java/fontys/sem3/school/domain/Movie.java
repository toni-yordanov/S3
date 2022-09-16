package fontys.sem3.school.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Movie extends Media{
    private int runtime;

    @Builder
    public Movie(long id, String name, String description, int runtime){
    super(id,name,description);
    this.runtime = runtime;
    }
}
