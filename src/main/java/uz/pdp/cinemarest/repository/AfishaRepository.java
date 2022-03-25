package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.Afisha;
import uz.pdp.cinemarest.projection.CustomAfisha;

import java.util.List;

public interface AfishaRepository extends JpaRepository<Afisha,Integer> {

}
