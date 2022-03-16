package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarest.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
