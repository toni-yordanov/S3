package fontys.sem3.school.controller;

import fontys.sem3.school.business.WatchlistService;
import fontys.sem3.school.persistence.entity.WatchlistEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchlist")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WatchlistController {
    private final WatchlistService watchlistService;

    @GetMapping
    public ResponseEntity<List<WatchlistEntity>> getWatchlists() {
        List<WatchlistEntity> watchlists = watchlistService.getWatchlists();
        return new ResponseEntity<>(watchlists, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WatchlistEntity>> getWatchlistForUser(@PathVariable Long userId) {
        List<WatchlistEntity> watchlist = watchlistService.getWatchlistForUser(userId);
        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WatchlistEntity> saveWatchlist(@RequestBody WatchlistEntity watchlist) {
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
