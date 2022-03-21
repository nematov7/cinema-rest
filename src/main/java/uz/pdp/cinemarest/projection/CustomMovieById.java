package uz.pdp.cinemarest.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;


public interface CustomMovieById {

    Integer getId();

    String getTitle();

    Integer getCoverImgId();

    LocalDate getReleaseDate();

    @Value("#{@genreRepository.getGenresByMovieId(target.id)}")
    List<GenreProjection> getGenres();


}
