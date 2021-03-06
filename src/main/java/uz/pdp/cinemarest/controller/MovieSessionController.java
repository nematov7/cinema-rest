package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.entity.MovieSession;
import uz.pdp.cinemarest.service.MovieSessionDto;
import uz.pdp.cinemarest.service.


        MovieSessionService;
import uz.pdp.cinemarest.utill.Constants;

@RestController
@RequestMapping("/movie-session")
public class MovieSessionController {
    @Autowired
    MovieSessionService movieSessionService;

    @GetMapping
    public HttpEntity<?> getAllMovieSession(
            @RequestParam(name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search
    ) {
        return movieSessionService.getAllMovieSessions(
                page,
                size,
                search);
    }

    @PostMapping
    public HttpEntity<?> saveMovieSession(@RequestBody MovieSessionDto movieSession) {
        return null;
    }


}
