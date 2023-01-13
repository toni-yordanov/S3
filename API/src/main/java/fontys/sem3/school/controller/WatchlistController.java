package fontys.sem3.school.controller;

import fontys.sem3.school.business.UserService;
import fontys.sem3.school.business.WatchlistService;
import fontys.sem3.school.persistence.entity.UserEntity;
import fontys.sem3.school.persistence.entity.WatchlistEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/watchlist")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WatchlistController {
    private final WatchlistService watchlistService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<WatchlistEntity>> getWatchlists() {
        List<WatchlistEntity> watchlists = watchlistService.getWatchlists();
        return new ResponseEntity<>(watchlists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchlistEntity> getWatchlist(@PathVariable Long id) {
        List<WatchlistEntity> watchlists = watchlistService.getWatchlists();

        Optional<WatchlistEntity> foundObject = watchlists.stream()
                .filter(o -> o.getId() == id)
                .findFirst();

        if(foundObject.isPresent()) {
            WatchlistEntity object = foundObject.get();
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
        return null;

    }

    @GetMapping("/personal")
    public ResponseEntity<List<WatchlistEntity>> getWatchlistForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity loggedInUser = this.userService.getUser(currentPrincipalName);
        List<WatchlistEntity> watchlist = watchlistService.getWatchlistForUser(loggedInUser.getId());
        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WatchlistEntity> saveWatchlist(@RequestBody WatchlistEntity watchlist) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity loggedInUser = this.userService.getUser(currentPrincipalName);
        watchlist.setUserId(loggedInUser.getId());

        WatchlistEntity savedWatchlist = watchlistService.saveWatchlist(watchlist);
        return new ResponseEntity<>(savedWatchlist, HttpStatus.CREATED);
    }

    @PutMapping("/{watchlistId}")
    public ResponseEntity<WatchlistEntity> updateWatchlist(@PathVariable Long watchlistId, @RequestBody WatchlistEntity watchlist) {
        WatchlistEntity updatedWatchlist = watchlistService.updateWatchlist(watchlistId, watchlist);
        return new ResponseEntity<>(updatedWatchlist, HttpStatus.OK);
    }

    @DeleteMapping("/{watchlistId}")
    public ResponseEntity<Void> deleteWatchlist(@PathVariable Long watchlistId) {
        watchlistService.deleteWatchlist(watchlistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
