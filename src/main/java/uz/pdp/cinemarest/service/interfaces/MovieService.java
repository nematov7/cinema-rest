package uz.pdp.cinemarest.service.interfaces;

import org.springframework.http.HttpEntity;
import uz.pdp.cinemarest.dto.MovieDto;

public interface MovieService {
    HttpEntity getAllMovies (int page,int size,String search ,String sort,boolean direction);
    HttpEntity getMovieById(Integer id);
    HttpEntity saveMovie(MovieDto movieDto);
    HttpEntity deleteMovie(Integer id);
    HttpEntity<?> editMovie(MovieDto movieDto, Integer id);


}
