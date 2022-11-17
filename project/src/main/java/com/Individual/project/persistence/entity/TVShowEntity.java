package fontys.sem3.school.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tvshow")
public class TVShowEntity extends MediaEntity{

    @Column(name = "episodes")
    private int episodes;

    @Builder
    public TVShowEntity(long id,String name, String description, int episodes){
        super(id, name, description);
        this.episodes = episodes;
    }
}
