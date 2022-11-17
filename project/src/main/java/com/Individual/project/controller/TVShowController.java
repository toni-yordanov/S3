package fontys.sem3.school.controller;

import fontys.sem3.school.business.*;
import fontys.sem3.school.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/tvshows")
@AllArgsConstructor
@CrossOrigin
public class TVShowController {

    private final GetTVShowsUseCase getTVShowsUseCase;
    private final DeleteTVShowUseCase deleteTVShowUseCase;
    private final GetTVShowUseCase getTVShowUseCase;
    private final CreateTVShowUseCase createTVShowUseCase;
    private final UpdateTVShowUseCase updateTVShowUseCase;

    @GetMapping
    public ResponseEntity<GetAllTVShowsResponse> getTVShows() {
        return ResponseEntity.ok(getTVShowsUseCase.getTVShows());
    }

    @GetMapping("{id}")
    public ResponseEntity<TVShow> getTVShow(@PathVariable(value = "id") final long id){
        final Optional<TVShow> tvShowOptional = getTVShowUseCase.getTVShow(id);
        if(tvShowOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(tvShowOptional.get());
    }

    @DeleteMapping("{showId}")
    public ResponseEntity<Void> deleteTVShow(@PathVariable int showId){
        deleteTVShowUseCase.deleteTVShow(showId);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CreateTVShowResponse> createTVShow(@RequestBody @Valid CreateTVSowRequest request){
        CreateTVShowResponse response = createTVShowUseCase.createTVShow(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateTVShow(@PathVariable("id") long id,
                                            @RequestBody @Valid UpdateTVShowRequest request){
        request.setId(id);
        updateTVShowUseCase.updateTVShow(request);
        return ResponseEntity.noContent().build();
    }
}
