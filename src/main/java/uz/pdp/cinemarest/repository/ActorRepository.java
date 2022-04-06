package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.Actor;
import uz.pdp.cinemarest.projection.ActorProjection;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Query(nativeQuery = true,value = "select a.name from actor a")
    List<ActorProjection> getAllActor();
}
