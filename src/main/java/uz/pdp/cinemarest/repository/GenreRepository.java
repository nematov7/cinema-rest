package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.cinemarest.entity.Genre;
import uz.pdp.cinemarest.projection.CustomGenre;
import uz.pdp.cinemarest.projection.GenreProjection;

import java.util.List;

@RepositoryRestResource(path = "genre",collectionResourceRel = "list",excerptProjection = CustomGenre.class)
public interface GenreRepository extends JpaRepository<Genre,Integer> {


    @Query(nativeQuery = true,value = "select g.id ,g.name from movie_genres\n" +
            "join genre g on movie_genres.genres_id = g.id\n" +
            "where movie_id= :id")
    List<GenreProjection> getGenresesByMovieId(Integer id);

}
