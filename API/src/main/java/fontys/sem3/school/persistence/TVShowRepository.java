package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.entity.TVShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TVShowRepository extends JpaRepository<TVShowEntity, Long> {
}
