package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.Row;
import uz.pdp.cinemarest.projection.CustomRow;

import java.util.List;

public interface RowRepository extends JpaRepository<Row,Integer> {
 @Query(value = "SELECT r.id,\n" +
         "       r.number,\n" +
         "       h.name                as hall\n" +
         "FROM row r\n" +
         "         join hall h on r.hall_id = h.id\n" +
         "where h.id =:hallId", nativeQuery = true)
 List<CustomRow>getRowsByHallId(Integer hallId);


 @Query(nativeQuery = true,value = "select\n" +
         "    max( r.number)\n" +
         "from\n" +
         "     row r\n" +
         "join hall h on h.id = r.hall_id\n" +
         "where r.hall_id=:hallId")
 int getLastRowNumber(Integer hallId);
}
