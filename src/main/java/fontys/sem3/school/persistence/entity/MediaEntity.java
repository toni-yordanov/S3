package fontys.sem3.school.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
public class MediaEntity {
    private Long id;
    private String name;
    private String description;
}
