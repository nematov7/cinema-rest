package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarest.entity.Attachment;
import uz.pdp.cinemarest.entity.AttachmentContent;
import uz.pdp.cinemarest.repository.AttachmentContentRepository;
import uz.pdp.cinemarest.repository.AttachmentRepository;
import uz.pdp.cinemarest.service.interfaces.AttachmentService;

import java.io.IOException;
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository  attachmentContentRepository;


    @Override
    public Attachment saveAttachment(MultipartFile file) {
        try {

            Attachment attachment = new Attachment();
            attachment.setName(file.getName());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            attachmentRepository.save(attachment);
            attachmentContentRepository.save(new AttachmentContent(attachment,file.getBytes()));
            return attachment;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
