package fontys.sem3.school.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TVShow extends Media{
    private int episodes;

    @Builder
    public TVShow(long id, String name, String description, int episodes){
        super(id,name,description);
        this.episodes = episodes;
    }
}
