package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarest.entity.Director;

public interface DirectorRepository extends JpaRepository<Director,Integer> {
}
