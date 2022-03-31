package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.Seat;
import uz.pdp.cinemarest.projection.AvailableSeatProjection;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Query(nativeQuery = true,
            value = "select s.id  as id,\n" +
                    "                   s.number              as seatNumber,\n" +
                    "                   hr.number             as rowNumber,\n" +
                    "                   true                  as available\n" +
                    "            from seat s\n" +
                    "                     join row hr on s.row_id = hr.id\n" +
                    "                     join hall h on hr.hall_id = h.id\n" +
                    "                     join movie_session ms on h.id = ms.hall_id\n" +
                    "            where s.id not in (\n" +
                    "                select t.seat_id\n" +
                    "                from ticket t\n" +
                    "                         join movie_session ms on ms.id = t.movie_session_id\n" +
                    "                where t.ticket_status <> 'REFUNDED'\n" +
                    "                  and ms.id = :movieSessionId\n" +
                    "            )\n" +
                    "              and ms.id = :movieSessionId\n" +
                    "            union\n" +
                    "            select s.id  as id,\n" +
                    "                   s.number              as seatNumber,\n" +
                    "                   hr.number             as rowNumber,\n" +
                    "                   false                 as available\n" +
                    "            from ticket t\n" +
                    "                     join seat s on t.seat_id = s.id\n" +
                    "                     join row hr on s.row_id = hr.id\n" +
                    "                     join movie_session ms on ms.id = t.movie_session_id\n" +
                    "            where t.ticket_status <> 'REFUNDED'\n" +
                    "              and ms.id = :movieSessionId\n" +
                    "            order by rowNumber, seatNumber")
    List<AvailableSeatProjection> getAvailableSeatsByMovieSessionId(Integer movieSessionId);

}
