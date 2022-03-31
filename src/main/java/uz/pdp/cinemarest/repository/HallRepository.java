package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.Hall;
import uz.pdp.cinemarest.projection.HallAndTimesProjectionForSession;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Integer> {

    @Query(nativeQuery = true, value = "select distinct\n" +
            "             h.id  as id,\n" +
            "                   h.name as name,\n" +
            "             rh.start_date_id  as startDateId,\n" +
            "             movie_announcement_id  as movieAnnouncementId\n" +
            "            from hall h \n" +
            "                     join movie_session rh on h.id = rh.hall_id\n" +
            "            where rh.start_date_id = :startDateId\n" +
            "              and movie_announcement_id = :movieAnnouncementId")
    List<HallAndTimesProjectionForSession> getHallsAndTimesByMovieAnnouncementIdAndStartDateId(Integer movieAnnouncementId, Integer startDateId);
}
