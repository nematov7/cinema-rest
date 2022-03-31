package uz.pdp.cinemarest.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface HallAndTimesProjectionForSession {

    Integer getId();

    String getName();


//    Integer getStartDateId();
//    Integer getMovieAnnouncementId();
//

    @Value("#{@sessionTimeRepository.getTimesByHallIdAndAnnouncementIdAndStartDateId(target.id, target.movieAnnouncementId, target.startDateId)}")
    List<SessionTimeProjection> getTimes();
}
