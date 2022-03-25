package uz.pdp.cinemarest.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinemarest.entity.Afisha;

import java.util.List;

@Projection(types = Afisha.class)
public interface CustomAfisha {
    Integer getId();
    Integer getMovieId();

    String getMovieTitle();

    Integer getMovieCoverImgId();

    Integer getStartedDateId();



    @Value("#{@hallRepository.getAllHallByMovieId(target.id)}")
    List<CustomHall> getHalls();
}
