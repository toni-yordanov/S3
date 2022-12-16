package fontys.sem3.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tvshows")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WatchlistController {
}
