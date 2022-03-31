package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarest.entity.Attachment;
import uz.pdp.cinemarest.entity.AttachmentContent;
import uz.pdp.cinemarest.repository.AttachmentContentRepository;
import uz.pdp.cinemarest.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/getFile")
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @GetMapping("/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();
                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
            }
        }
    }
}
