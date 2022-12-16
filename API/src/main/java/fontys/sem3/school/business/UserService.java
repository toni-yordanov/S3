package fontys.sem3.school.business;

import fontys.sem3.school.persistence.entity.RoleEntity;
import fontys.sem3.school.persistence.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity role);
    void addRoleToUser(String email, String roleName);
    UserEntity getUser(String email);
    List<UserEntity> getUsers();
}
