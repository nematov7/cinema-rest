package uz.pdp.cinemarest.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinemarest.entity.Row;

@Projection(types = Row.class)
public interface CustomRow {

    int getId();

    Integer getNumber();

    String getHall();
}
