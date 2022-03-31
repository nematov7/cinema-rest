package uz.pdp.cinemarest.projection;

import java.time.LocalTime;

public interface SessionTimeProjection {

    Integer getId();

    LocalTime getTime();

    Integer getSessionId();
}
