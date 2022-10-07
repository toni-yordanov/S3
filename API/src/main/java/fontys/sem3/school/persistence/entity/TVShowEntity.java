package fontys.sem3.school.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TVShowEntity extends MediaEntity{
    private int episodes;

    @Builder
    public TVShowEntity(long id,String name, String description, int episodes){
        super(id, name, description);
        this.episodes = episodes;
    }
}
