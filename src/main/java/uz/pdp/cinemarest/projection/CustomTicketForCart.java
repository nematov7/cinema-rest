package uz.pdp.cinemarest.projection;

import uz.pdp.cinemarest.entity.enums.TicketStatus;

public interface CustomTicketForCart {
    Integer getTicketId();
    TicketStatus getTicketStatus();
    String getMovieTitle();
    Double getPrice();

}
