package fontys.sem3.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTVSowRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Min(1)
    private int episodes;
}
