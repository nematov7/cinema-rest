package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarest.entity.Actor;
import uz.pdp.cinemarest.dto.ActorDto;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.service.ActorService;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    ActorService actorService;

    @PostMapping("/save")
    public HttpEntity<?> saveActor(@RequestParam("file") MultipartFile file,
                                   @RequestParam("json") ActorDto actorDto) {
        Actor actor = actorService.saveActor(file, actorDto);
        if (actor != null) {
            return new ResponseEntity(new ApiResponse("success", true, actor), HttpStatus.OK);
        } else
            return new ResponseEntity(new ApiResponse("Wrong", false, null), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteActor(@PathVariable int id) {
        boolean b = actorService.deleteActor(id);

        if (b)
            return new ResponseEntity("deleted", HttpStatus.OK);
        else
            return new ResponseEntity("not deleted", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public HttpEntity editActor(@PathVariable Integer id,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam("json") ActorDto actorDto) {
        boolean b = actorService.editActor(id,file, actorDto);
        if (b) {
            return new ResponseEntity(new ApiResponse("success",
                    true, true), HttpStatus.OK);
        }
        else  return new ResponseEntity(new ApiResponse("wrong",
                false, false), HttpStatus.BAD_REQUEST);
    }
}
