package fontys.sem3.school.business.impl;

import fontys.sem3.school.persistence.RoleRepository;
import fontys.sem3.school.persistence.UserRepository;
import fontys.sem3.school.persistence.entity.RoleEntity;
import fontys.sem3.school.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepo;

    @Mock
    private RoleRepository roleRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testLoadUserByUsername() {
        UserEntity user = new UserEntity(1L, "Test","test@test.com",  "password", null);
        when(userRepo.findByEmail(anyString())).thenReturn(user);

        UserEntity userDetails = userService.getUser("test@test.com");

        assertEquals("test@test.com", userDetails.getEmail());
        assertEquals("password", userDetails.getPassword());
    }

    @Test
    public void testSaveUser() {
        UserEntity user = new UserEntity(1L, "Test","test@test.com",  "encoded_password", null);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(userRepo.save(any(UserEntity.class))).thenReturn(user);

        UserEntity savedUser = userService.saveUser(user);

        assertEquals("test@test.com", savedUser.getEmail());
        assertEquals("encoded_password", savedUser.getPassword());
    }

    @Test
    public void testSaveRole() {
        RoleEntity role = new RoleEntity(1l,"ROLE_USER");
        when(roleRepo.save(any(RoleEntity.class))).thenReturn(role);

        RoleEntity savedRole = userService.saveRole(role);

        assertEquals("ROLE_USER", savedRole.getName());
    }

    @Test
    public void testAddRoleToUser() {
        UserEntity user = new UserEntity(1L, "Test","test@test.com",  "password", new ArrayList<>());
        when(userRepo.findByEmail(anyString())).thenReturn(user);
        RoleEntity role = new RoleEntity(1l,"ROLE_USER");
        when(roleRepo.findByName(anyString())).thenReturn(role);

        userService.addRoleToUser("test@test.com", "ROLE_USER");

        assertTrue(user.getRoles().contains(role));
    }

    @Test
    public void testGetUsers() {
        List<UserEntity> users = new ArrayList<>();
        users.add( new UserEntity(1L, "Test","test1@test.com",  "password1", null));
        users.add( new UserEntity(2L, "Test","test2@test.com",  "password2", null));
        when(userRepo.findAll()).thenReturn(users);

        List<UserEntity> returnedUsers = userService.getUsers();

        assertEquals(2, returnedUsers.size());
        assertEquals("test1@test.com", returnedUsers.get(0).getEmail());
        assertEquals("password1", returnedUsers.get(0).getPassword());
        assertEquals("test2@test.com", returnedUsers.get(1).getEmail());
        assertEquals("password2", returnedUsers.get(1).getPassword());
    }
}
