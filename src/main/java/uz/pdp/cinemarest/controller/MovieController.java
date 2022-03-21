package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.dto.MovieDto;
import uz.pdp.cinemarest.repository.MovieRepository;
import uz.pdp.cinemarest.service.MovieServiceImpl;
import uz.pdp.cinemarest.utill.Constants;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieServiceImpl movieService;

    @Autowired
    MovieRepository movieRepository;

    /*------------------ Advanced pagination------------------ */

    @GetMapping
    public HttpEntity getAllMovies(
            @RequestParam(name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "sort", defaultValue = "title") String sort
    ) {
        return movieService.getAllMovies(page, size, search, sort, true);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveMovie(MovieDto movie) {
        System.out.println(movie);
        movieService.saveMovie(movie);
        return new ResponseEntity("save", HttpStatus.OK);
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}, path = "/{id}")
    public ResponseEntity editMovie(MovieDto movie, @PathVariable Integer id) {
        System.out.println(movie);
        movieService.editMovie(movie, id);
        return new ResponseEntity("save", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMovie(@PathVariable int id) {
        return movieService.deleteMovie(id);
    }


}
