package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarest.entity.MovieSession;

public interface AfishaRepository extends JpaRepository<MovieSession,Integer> {

}
