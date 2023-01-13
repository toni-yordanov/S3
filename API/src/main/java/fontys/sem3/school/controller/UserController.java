package fontys.sem3.school.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.school.business.UserService;
import fontys.sem3.school.persistence.entity.RoleEntity;
import fontys.sem3.school.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class UserController {
    private final UserService userService;


    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok().body(userService.getUser(currentPrincipalName));
    }
    @GetMapping()
    public ResponseEntity<List<UserEntity>>getUsers(){
        return  ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity>saveUsers(@RequestBody UserEntity user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/register").toUriString());
        return  ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<RoleEntity>saveUsers(@RequestBody RoleEntity role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/role/save").toUriString());
        return  ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>saveUsers(@RequestBody String email){
        userService.addRoleToUser(email, "ROLL_USER");
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshTokeb(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String header = request.getHeader(AUTHORIZATION);
        if(header != null && header.startsWith("Bearer ")){
            try {
                String refreshToken = header.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                UserEntity user = userService.getUser(username);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                String accessToken = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }catch (Exception exception){
                log.error("Error logging in {}", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String > error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else {
            throw new RuntimeException("Refresh token is missing");
        }
    }


}

