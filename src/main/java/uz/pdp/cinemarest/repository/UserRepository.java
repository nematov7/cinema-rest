package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarest.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
