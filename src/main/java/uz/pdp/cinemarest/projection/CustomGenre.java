package uz.pdp.cinemarest.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinemarest.entity.Genre;

@Projection(types = Genre.class)
public class CustomGenre {

}
