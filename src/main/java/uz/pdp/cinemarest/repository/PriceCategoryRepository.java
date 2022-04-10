package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarest.entity.PriceCategory;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory ,Integer> {
}
