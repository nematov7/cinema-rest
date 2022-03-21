package uz.pdp.cinemarest.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarest.entity.Attachment;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file);
}
