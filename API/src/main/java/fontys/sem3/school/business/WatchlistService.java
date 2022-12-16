package fontys.sem3.school.business;

import fontys.sem3.school.persistence.entity.WatchlistEntity;

import java.util.List;

public interface WatchlistService {

    WatchlistEntity saveWatchlist(WatchlistEntity watchlist);
    List<WatchlistEntity> getWatchlistForUser(Long userid);
    List<WatchlistEntity> getWatchlists();
}
