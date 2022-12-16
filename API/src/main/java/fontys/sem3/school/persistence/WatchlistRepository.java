package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.entity.WatchlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<WatchlistEntity, Long> {

}
