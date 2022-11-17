package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
