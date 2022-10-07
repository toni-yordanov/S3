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
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {
    private final GetMoviesUseCase getMoviesUseCase;
    private final DeleteMovieUseCase deleteMovieUseCase;
    private final GetMovieUseCase getMovieUseCase;
    private final CreateMovieUseCase createMovieUseCase;
    private final UpdateMovieUseCase updateMovieUseCase;

    @GetMapping
    public ResponseEntity<GetAllMoviesResponse> getMovies() {
        return ResponseEntity.ok(getMoviesUseCase.getMovies());
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable(value = "id") final long id){
        final Optional<Movie> movieOptional = getMovieUseCase.getMovie(id);
        if(movieOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movieOptional.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id){
        deleteMovieUseCase.deleteMovie(id);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CreateMovieResponse> createMovie(@RequestBody @Valid CreateMovieRequest request){
        CreateMovieResponse response = createMovieUseCase.createMovie(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable("id") long id,
                                             @RequestBody @Valid UpdateMovieRequest request){
        request.setId(id);
        updateMovieUseCase.updateMovie(request);
        return ResponseEntity.noContent().build();
    }
}
