package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.UserService;
import fontys.sem3.school.persistence.RoleRepository;
import fontys.sem3.school.persistence.UserRepository;
import fontys.sem3.school.persistence.entity.RoleEntity;
import fontys.sem3.school.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(username);
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
    public UserEntity saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        UserEntity user = userRepo.findByEmail(email);
        RoleEntity role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserEntity getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepo.findAll();
    }


}
