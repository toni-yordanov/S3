package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.WatchlistService;
import fontys.sem3.school.persistence.WatchlistRepository;
import fontys.sem3.school.persistence.entity.WatchlistEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WatchlistServiceImpl implements WatchlistService {
    private final WatchlistRepository watchlistRepository;


    @Override
    public WatchlistEntity saveWatchlist(WatchlistEntity watchlist) {
        watchlistRepository.save(watchlist);
        return watchlist;
    }

    @Override
    public List<WatchlistEntity> getWatchlistForUser(Long userid) {
        ArrayList<WatchlistEntity> watchlistUser = new ArrayList<>();
        for (WatchlistEntity w:
             watchlistRepository.findAll()) {
            if(w.getUserId().equals(userid)){
                watchlistUser.add(w);
            }
        }
        return watchlistUser;
    }

    @Override
    public List<WatchlistEntity> getWatchlists() {
        return  watchlistRepository.findAll();
    }

    @Override
    public WatchlistEntity updateWatchlist(Long watchlistId, WatchlistEntity watchlist) {
        WatchlistEntity existingWatchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Watchlist not found with id " + watchlistId));

        existingWatchlist.setType(watchlist.getType());
        existingWatchlist.setMediaId(watchlist.getMediaId());
        existingWatchlist.setCompletionDate(watchlist.getCompletionDate());
        existingWatchlist.setStatus(watchlist.getStatus());

        WatchlistEntity updatedWatchlist = watchlistRepository.save(existingWatchlist);
        return updatedWatchlist;
    }

    @Override
    public void deleteWatchlist(Long watchlistId) {
        WatchlistEntity watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Watchlist not found with id " + watchlistId));
        watchlistRepository.delete(watchlist);
    }
}
