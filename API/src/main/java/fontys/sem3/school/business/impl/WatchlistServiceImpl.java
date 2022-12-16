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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = null;//userRepo.findByEmail(username);
        if(user == null){
            log.error("User not found in database");
            throw  new UsernameNotFoundException("User not found in database");
        }
        else {
            log.info("User found in database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roleEntity -> authorities.add(new SimpleGrantedAuthority(roleEntity.getName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }


    @Override
    public WatchlistEntity saveWatchlist(WatchlistEntity watchlist) {
        return null;
    }

    @Override
    public List<WatchlistEntity> getWatchlistForUser(Long userid) {
        ArrayList<WatchlistEntity> watchlistUser = new ArrayList<>();
        for (WatchlistEntity w:
             watchlistRepository.findAll()) {
            if(w.getUserId() == userid){
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
