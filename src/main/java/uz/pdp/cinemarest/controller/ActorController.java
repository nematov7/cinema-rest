package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarest.dto.ActorDto;
import uz.pdp.cinemarest.service.ActorService;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    ActorService actorService;

    @PostMapping("/save")
    public HttpEntity<?> saveActor(@RequestPart(name ="file") MultipartFile file,
                                   @RequestPart(name = "actor") ActorDto actorDto) {
        return actorService.saveActor(file, actorDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteActor(@PathVariable int id) {
       return actorService.deleteActor(id);
    }



    @PutMapping("/{id}")
    public HttpEntity editActor(@PathVariable Integer id,
                                @RequestPart(value = "file") MultipartFile file,
                                @RequestPart(value = "actor") ActorDto actorDto) {
      return actorService.editActor(id, file, actorDto);
    }

    @GetMapping
    public HttpEntity getAllActor(){
      return   actorService.getAllActor();
    }


}
