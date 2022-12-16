package fontys.sem3.school.persistence;


import fontys.sem3.school.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail (String email);

}
