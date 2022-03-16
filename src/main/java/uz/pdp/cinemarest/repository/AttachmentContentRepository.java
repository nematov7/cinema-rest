package uz.pdp.cinemarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarest.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
}
