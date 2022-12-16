package fontys.sem3.school.persistence;

import fontys.sem3.school.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
