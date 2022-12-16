package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.WatchlistService;
import fontys.sem3.school.persistence.WatchlistRepository;
import fontys.sem3.school.persistence.entity.UserEntity;
import fontys.sem3.school.persistence.entity.WatchlistEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
}
