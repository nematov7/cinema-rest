package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.GenreDto;
import uz.pdp.cinemarest.entity.Genre;
import uz.pdp.cinemarest.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public HttpEntity<?> saveGenre(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        try {
            Genre save = genreRepository.save(genre);
            return new ResponseEntity(new ApiResponse("success", true, save), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.BAD_REQUEST);
        }
    }

    public HttpEntity<?> getAll() {
        try {
            List<Genre> all = genreRepository.findAll();
            if (all.size() == 0)
                return new ResponseEntity(new ApiResponse("success", true, "Empty List"), HttpStatus.OK);

            return new ResponseEntity(new ApiResponse("success", true, all), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.BAD_REQUEST);
        }
    }

    public HttpEntity<?> getById(Integer id) {

        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) return new ResponseEntity(new ApiResponse("success", true, genre), HttpStatus.OK);
        return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.BAD_REQUEST);
    }

    public HttpEntity<?> editGenre(Integer id, GenreDto genreDto) {
        try {
            Optional<Genre> genre = genreRepository.findById(id);
            genre.get().setName(genreDto.getName());

            Genre save = genreRepository.save(genre.get());
            return new ResponseEntity(new ApiResponse("success", true, save), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.BAD_REQUEST);
        }
    }

    public HttpEntity<?> deleteGenre(Integer id) {
        try {
            genreRepository.deleteById(id);

            return new ResponseEntity(new ApiResponse("success", true, "delete genre"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.BAD_REQUEST);
        }
    }
}
