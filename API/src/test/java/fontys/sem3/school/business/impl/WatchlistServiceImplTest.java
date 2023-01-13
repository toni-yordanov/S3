package fontys.sem3.school.business.impl;

import fontys.sem3.school.persistence.WatchlistRepository;
import fontys.sem3.school.persistence.entity.WatchlistEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WatchlistServiceImplTest {
    @Mock
    private WatchlistRepository watchlistRepo;

    @InjectMocks
    private WatchlistServiceImpl watchlistService;

    @Test
    public void testSaveWatchlist() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date completionDate = formatter.parse("2022-01-01");
        WatchlistEntity watchlist = new WatchlistEntity(1L, 2L, "movie", 1L,completionDate, "completed");
        when(watchlistRepo.save(any(WatchlistEntity.class))).thenReturn(watchlist);

        WatchlistEntity savedWatchlist = watchlistService.saveWatchlist(watchlist);

        assertEquals(2L, savedWatchlist.getUserId());
        assertEquals(1L, savedWatchlist.getId());
        assertEquals("movie", savedWatchlist.getType());
        assertEquals(1L, savedWatchlist.getMediaId());
        assertEquals(completionDate, savedWatchlist.getCompletionDate());
        assertEquals("completed", savedWatchlist.getStatus());
    }

    @Test
    public void testGetWatchlistForUser() throws ParseException {
        List<WatchlistEntity> watchlists = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date completionDate = formatter.parse("2022-01-01");
        WatchlistEntity watchlist1 = new WatchlistEntity(1L, 2L, "movie", 1L, completionDate, "completed");
        WatchlistEntity watchlist2 = new WatchlistEntity(1L, 2L, "series", 2L, completionDate, "in progress");
        watchlists.add(watchlist1);
        watchlists.add(watchlist2);
        when(watchlistRepo.findAll()).thenReturn(watchlists);

        List<WatchlistEntity> userWatchlists = watchlistService.getWatchlistForUser(2L);

        assertEquals(2, userWatchlists.size());
        assertTrue(userWatchlists.contains(watchlist1));
        assertTrue(userWatchlists.contains(watchlist2));
    }

    @Test
    public void testGetWatchlists() throws ParseException {
        List<WatchlistEntity> watchlists = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date completionDate = formatter.parse("2022-01-01");
        WatchlistEntity watchlist1 = new WatchlistEntity(1L, 2L, "movie", 1L, completionDate, "completed");
        WatchlistEntity watchlist2 = new WatchlistEntity(2L, 3L, "series", 2L, completionDate, "in progress");
        watchlists.add(watchlist1);
        watchlists.add(watchlist2);
        when(watchlistRepo.findAll()).thenReturn(watchlists);

        List<WatchlistEntity> returnedWatchlists = watchlistService.getWatchlists();

        assertEquals(2, returnedWatchlists.size());
        assertTrue(returnedWatchlists.contains(watchlist1));
        assertTrue(returnedWatchlists.contains(watchlist2));
    }

    @Test
    public void testUpdateWatchlist() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date completionDate = formatter.parse("2022-01-01");
        Date completionDate2 = formatter.parse("2022-02-01");

        WatchlistEntity watchlist = new WatchlistEntity(1L, 2L, "movie", 1L, completionDate, "completed");
        when(watchlistRepo.findById(any(Long.class))).thenReturn(java.util.Optional.of(watchlist));
        when(watchlistRepo.save(any(WatchlistEntity.class))).thenReturn(watchlist);

        WatchlistEntity updatedWatchlist = watchlistService.updateWatchlist(1L, new WatchlistEntity(1L, 2L, "series", 2L, completionDate2, "completed"));

        assertEquals(2L, updatedWatchlist.getUserId());
        assertEquals(1L, updatedWatchlist.getId());
        assertEquals("series", updatedWatchlist.getType());
        assertEquals(2L, updatedWatchlist.getMediaId());
        assertEquals(completionDate2, updatedWatchlist.getCompletionDate());
        assertEquals("completed", updatedWatchlist.getStatus());
    }

    @Test
    public void testDeleteWatchlist() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date completionDate = formatter.parse("2022-01-01");
        WatchlistEntity watchlist = new WatchlistEntity(1L, 2L, "movie", 1L, completionDate, "completed");
        when(watchlistRepo.findById(any(Long.class))).thenReturn(java.util.Optional.of(watchlist));
        watchlistService.deleteWatchlist(1L);

        // Verify that the watchlistRepo.delete() method is called once
        verify(watchlistRepo, times(1)).delete(watchlist);
    }
}