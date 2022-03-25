package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.Hall;
import uz.pdp.cinemarest.projection.CustomHall;
import uz.pdp.cinemarest.projection.CustomMovieById;

import java.util.Optional;

public interface HalRepository extends JpaRepository<Hall, Integer> {
}
