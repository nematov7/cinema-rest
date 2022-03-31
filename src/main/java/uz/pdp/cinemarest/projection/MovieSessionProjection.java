package uz.pdp.cinemarest.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovieSessionProjection {
    Integer getMovieAnnouncementId();

    Integer getMovieId();

    String getMovieTitle();

    Integer getMovieCoverImgId();

    Integer getStartDateId();

    LocalDate getStartDate();

    @Value("#{@hallRepository.getHallsAndTimesByMovieAnnouncementIdAndStartDateId(target.movieAnnouncementId, target.startDateId)}")
    List<HallAndTimesProjectionForSession> getHalls();
}
