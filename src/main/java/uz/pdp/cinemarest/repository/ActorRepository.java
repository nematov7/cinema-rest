package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarest.entity.Actor;
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
