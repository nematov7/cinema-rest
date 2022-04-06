package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.entity.Actor;
import uz.pdp.cinemarest.entity.Attachment;
import uz.pdp.cinemarest.entity.AttachmentContent;
import uz.pdp.cinemarest.dto.ActorDto;
import uz.pdp.cinemarest.projection.ActorProjection;
import uz.pdp.cinemarest.repository.ActorRepository;
import uz.pdp.cinemarest.repository.AttachmentContentRepository;
import uz.pdp.cinemarest.repository.AttachmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    ActorRepository actorRepository;


    public HttpEntity saveActor(MultipartFile file, ActorDto actorDto) {

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

            Actor saveActor = actorRepository.save(actor);

            return new ResponseEntity(new ApiResponse("success", true, saveActor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("Wrong", false, null), HttpStatus.NOT_FOUND);
        }


    }

    public HttpEntity deleteActor(int id) {

        try {
            Optional<Actor> byId = actorRepository.findById(id);
            Integer id1 = byId.get().getAttachment().getId();

            attachmentContentRepository.deleteAttachmentContent(id1);

            actorRepository.deleteById(id);

            attachmentRepository.deleteById(id1);

            return new ResponseEntity("deleted", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("not deleted", HttpStatus.BAD_REQUEST);
        }
    }



    public HttpEntity editActor(Integer id, MultipartFile file, ActorDto actorDto) {
        try {

            Actor actor = actorRepository.getById(id);

            actor.setName(actorDto.getFullName());

            if (file != null) {
                Integer attachmentId = actor.getAttachment().getId();
                try {
                attachmentContentRepository.deleteAttachmentContent(attachmentId);
                attachmentRepository.deleteById(attachmentId);
                }catch (Exception e){
                    e.printStackTrace();
                }

                Attachment attachment = new Attachment();

                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment.setName(file.getName());
                attachmentRepository.save(attachment);

                attachmentContentRepository.save(new AttachmentContent(attachment, file.getBytes()));
                actor.setAttachment(attachment);

                Actor saveActor = actorRepository.save(actor);

                return new ResponseEntity(new ApiResponse("success", true,saveActor ), HttpStatus.OK);
            }
            return new ResponseEntity(new ApiResponse("success", true, actorRepository.save(actor)), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("Wrong", false, null), HttpStatus.NOT_FOUND);
        }
    }

    public HttpEntity getAllActor() {
        List<ActorProjection> actorList = actorRepository.getAllActor();
        if (actorList.size()>0) {
            return new ResponseEntity(new ApiResponse("success", true,actorList), HttpStatus.OK);
        }
        return new ResponseEntity(new ApiResponse("Wrong", false, null), HttpStatus.NOT_FOUND);
    }
}
