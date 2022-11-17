package fontys.sem3.school.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class MovieEntity extends  MediaEntity{
    @Column(name = "runtime")
    private int runtime;

    @Builder
    public MovieEntity(long id,String name, String description, int runtime){
        super(id, name, description);
        this.runtime = runtime;
    }

}
