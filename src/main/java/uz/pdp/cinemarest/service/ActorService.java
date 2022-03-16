package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarest.entity.Actor;
import uz.pdp.cinemarest.entity.Attachment;
import uz.pdp.cinemarest.entity.AttachmentContent;
import uz.pdp.cinemarest.payload.ActorDto;
import uz.pdp.cinemarest.repository.ActorRepository;
import uz.pdp.cinemarest.repository.AttachmentContentRepository;
import uz.pdp.cinemarest.repository.AttachmentRepository;

@Service
public class ActorService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    ActorRepository actorRepository;


    public Actor saveActor(MultipartFile file, ActorDto actorDto) {

        try {
            Attachment attachment = new Attachment();

            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            attachment.setName(file.getName());
            attachmentRepository.save(attachment);

            attachmentContentRepository.save(new AttachmentContent(attachment, file.getBytes()));
            Actor actor = new Actor();
            actor.setName(actorDto.getFullName());
            actor.setAttachment(attachment);

            return actorRepository.save(actor);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteActor(int id) {
        try {
            actorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean editActor(Integer id, MultipartFile file, ActorDto actorDto) {
        try {
            Actor actor = actorRepository.getById(id);

            actor.setName(actorDto.getFullName());
            if (file != null) {
                Integer attachmentId = actor.getAttachment().getId();
                Attachment attachment = attachmentRepository.getById(attachmentId);
                attachment.setName(file.getName());
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachmentRepository.save(attachment);

                attachmentContentRepository.save(new AttachmentContent(attachment, file.getBytes()));
                actorRepository.save(actor);
                return true;
            }
            actorRepository.save(actor);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
