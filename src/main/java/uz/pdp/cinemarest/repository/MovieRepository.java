package uz.pdp.cinemarest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cinemarest.entity.Movie;
import uz.pdp.cinemarest.projection.CustomMovie;
import uz.pdp.cinemarest.projection.CustomMovieById;

import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "select\n" +
            "                   m.id  as id,\n" +
            "                   title,\n" +
            "                  a.id as coverImgId\n" +
            "            from movie  m\n" +
            "            join attachments a on a.id = m.cover_img_id\n" +
            "            where lower(title) like lower(concat('%', :search, '%'))", nativeQuery = true)
    Page<CustomMovie> findAllByPage(Pageable pageable, @Param("search") String search);


    @Query(nativeQuery = true, value = "select\n" +
            "           m.id as  id,\n" +
            "                  m.title,\n" +
            "                   m.cover_img_id as coverImgId\n" +
            "            from movie m\n" +
            "            where m.id = :id")
    Optional<CustomMovieById> getMovieById(Integer id);

}
