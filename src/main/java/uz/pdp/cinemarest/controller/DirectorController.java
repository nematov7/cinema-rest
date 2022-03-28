package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.dto.DirectorDto;
import uz.pdp.cinemarest.entity.Director;
import uz.pdp.cinemarest.service.DirectorService;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {
  @Autowired
  DirectorService directorService;

  @GetMapping
  public HttpEntity getAllDirector(){
    return directorService.getAllDirector();
  }

  @PostMapping
    public HttpEntity saveDirector(Director director){
     return directorService.saveDirector(director);
  }


  @PutMapping("/{id}")
  public HttpEntity editDirector(@PathVariable Integer id, DirectorDto directorDto){
    return directorService.editDirector(id,directorDto);
  }


  @DeleteMapping("/{id}")
  public HttpEntity deleteDirector(@PathVariable Integer id){
    return directorService.deleteDirector(id);
  }
}
