package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.MovieDto;
import uz.pdp.cinemarest.entity.*;
import uz.pdp.cinemarest.projection.CustomMovie;
import uz.pdp.cinemarest.repository.*;
import uz.pdp.cinemarest.service.interfaces.MovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DistributorRepository distributorRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    AttachmentServiceImpl attachmentService;

    @Override
    public HttpEntity getAllMovies(int page, int size, String search, String sort, boolean direction) {

        Pageable pageable = (Pageable) PageRequest.of(
                page - 1,
                size,
                direction ? Sort.Direction.ASC : Sort.Direction.DESC,
                sort
        );
        try {
            Page<CustomMovie> all = movieRepository.findAllByPage(
                    pageable,
                    search);

            return ResponseEntity.ok(new ApiResponse("success", true, all));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("error", false, null));

        }
    }

    @Override
    public HttpEntity getMovieById(Integer id) {
        if (!movieRepository.getMovieById(id).isPresent()) {
//            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("error", false, null));

        }
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("success", true,movieRepository.getMovieById(id).get()));
        return ResponseEntity.ok(new ApiResponse("success", true, movieRepository.getMovieById(id).isPresent()));
    }

    @Override
    public HttpEntity saveMovie(MovieDto movieDto) {


        Movie movie = new Movie();


        Optional<Distributor> distributorOptional = distributorRepository.findById(movieDto.getDistributorId());
        if (!distributorOptional.isPresent()) {
            return new ResponseEntity(new ApiResponse("wrong", false, null), HttpStatus.BAD_REQUEST);
        }

        ArrayList<Director> directors = new ArrayList<>();
        for (Integer directorId : movieDto.getDirectorIds()) {

            if (!directorRepository.findById(directorId).isPresent()) {
                return new ResponseEntity(new ApiResponse("wrong",
                        false, null), HttpStatus.BAD_REQUEST);
            }
            directors.add(directorRepository.findById(directorId).get());
        }

        ArrayList<Genre> genres = new ArrayList<>();
        for (Integer genreId : movieDto.getGenreIds()) {
            if (!genreRepository.findById(genreId).isPresent()) {
                return new ResponseEntity(new ApiResponse("wrong",
                        false, null), HttpStatus.BAD_REQUEST);
            }
            genres.add(genreRepository.findById(genreId).get());
        }


        ArrayList<Actor> actors = new ArrayList<>();
        for (Integer actorId : movieDto.getGenreIds()) {
            if (!actorRepository.findById(actorId).isPresent()) {
                return new ResponseEntity(new ApiResponse("wrong",
                        false, null), HttpStatus.BAD_REQUEST);
            }
            actors.add(actorRepository.findById(actorId).get());
        }


        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setDurationInMinutes(movieDto.getDurationInMinutes());
        movie.setMinPrice(movieDto.getMinPrice());
        movie.setDistributorShareInPercent(movieDto.getDistributorShareInPercent());
        movie.setCoverImg(attachmentService.saveAttachment(movieDto.getCoverImage()));
        movie.setTrailerVideo(attachmentService.saveAttachment(movieDto.getTrailerVideo()));
        movie.setDirectors(directors);
        movie.setActors(actors);
        movie.setGenres(genres);
        movie.setDistributor(distributorOptional.get());
        movieRepository.save(movie);
        return new ResponseEntity(new ApiResponse("success", true, movie), HttpStatus.OK);

    }

    @Override
    public HttpEntity deleteMovie(Integer id) {
        List<Movie> movieList = movieRepository.findAll();
        for (Movie movie : movieList) {
            if (movie.getId().equals(id)) {
                movieRepository.deleteById(id);
                return new ResponseEntity(new ApiResponse("succuss", true, true), HttpStatus.OK);
            }
        }
        return new ResponseEntity(new ApiResponse("wrong",
                false, false), HttpStatus.NOT_FOUND);
    }

    @Override
    public HttpEntity<?> editMovie(MovieDto movieDto, Integer id) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (!optionalMovie.isPresent()) {
            return new ResponseEntity(new ApiResponse("wrong",
                    false), HttpStatus.NOT_FOUND);
        }


        Optional<Distributor> distributorOptional = distributorRepository.findById(movieDto.getDistributorId());
        if (!distributorOptional.isPresent()) {
            return new ResponseEntity(new ApiResponse("wrong",
                    false), HttpStatus.BAD_REQUEST);
        }


        ArrayList<Director> directors = new ArrayList<>();
        for (Integer directorId : movieDto.getDirectorIds()) {
            if (!directorRepository.findById(directorId).isPresent()) {
                return new ResponseEntity(new ApiResponse("wrong",
                        false), HttpStatus.BAD_REQUEST);
            }
            directors.add(directorRepository.findById(directorId).get());
        }

        ArrayList<Genre> genres = new ArrayList<>();
        for (Integer genreId : movieDto.getGenreIds()) {
            if (genreRepository.findById(genreId).isPresent()) {
                return new ResponseEntity(new ApiResponse("wrong",
                        false, null), HttpStatus.BAD_REQUEST);
            }
            genres.add(genreRepository.findById(genreId).get());
        }
        ArrayList<Actor> actors = new ArrayList<>();
        for (Integer actorId : movieDto.getActorIds()) {
            if (!movieRepository.getMovieById(actorId).isPresent()) {
                return new ResponseEntity(new ApiResponse("wrong",
                        false, null), HttpStatus.BAD_REQUEST);
            }
        }
        Movie movie = optionalMovie.get();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setDurationInMinutes(movieDto.getDurationInMinutes());
        movie.setMinPrice(movieDto.getMinPrice());
        movie.setDistributorShareInPercent(movieDto.getDistributorShareInPercent());
        movie.setCoverImg(attachmentService.saveAttachment(movieDto.getCoverImage()));
        movie.setTrailerVideo(attachmentService.saveAttachment(movieDto.getTrailerVideo()));
        movie.setDirectors(directors);
        movie.setGenres(genres);
        movie.setActors(actors);
        movie.setDistributor(distributorOptional.get());
        movieRepository.save(movie);
        return new ResponseEntity(new ApiResponse("success",
                true, null), HttpStatus.OK);
    }
}
