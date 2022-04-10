package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.dto.GenreDto;
import uz.pdp.cinemarest.service.GenreService;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping
    public HttpEntity<?> saveGenre(@RequestBody GenreDto genreDto) {
        return genreService.saveGenre(genreDto);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        return genreService.getById(id);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editGenre(@PathVariable Integer id, @RequestBody GenreDto genreDto) {
        return genreService.editGenre(id, genreDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGenre(@PathVariable Integer id) {
        return genreService.deleteGenre(id);
    }
}
