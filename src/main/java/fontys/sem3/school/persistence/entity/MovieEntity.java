package fontys.sem3.school.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieEntity extends  MediaEntity{
    private int runtime;

    @Builder
    public MovieEntity(long id,String name, String description, int runtime){
        super(id, name, description);
        this.runtime = runtime;
    }
}
