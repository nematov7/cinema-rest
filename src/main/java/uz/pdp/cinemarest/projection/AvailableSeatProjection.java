package uz.pdp.cinemarest.projection;

public interface AvailableSeatProjection {
    Integer getId();

    Integer getSeatNumber();

    Integer getRowNumber();

    Boolean getAvailable();
}
