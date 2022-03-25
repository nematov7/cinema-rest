package uz.pdp.cinemarest.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinemarest.entity.Hall;

@Projection(types = Hall.class)
public interface CustomHall {
    Integer getId();
    String getName();
    double getVipAdditionalFeeInPercent();
}
