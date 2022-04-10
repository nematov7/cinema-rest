package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarest.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
//    User findByUsername(String username);
}
