package fontys.sem3.school.business;

import fontys.sem3.school.persistence.entity.WatchlistEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface WatchlistService {

    WatchlistEntity saveWatchlist(WatchlistEntity watchlist);
    List<WatchlistEntity> getWatchlistForUser(Long userid);
    List<WatchlistEntity> getWatchlists();
    WatchlistEntity updateWatchlist(Long watchlistId, WatchlistEntity watchlist);

    void deleteWatchlist(@PathVariable Long watchlistId);
}
