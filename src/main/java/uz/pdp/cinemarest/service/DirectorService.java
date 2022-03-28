package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.DirectorDto;
import uz.pdp.cinemarest.entity.Director;
import uz.pdp.cinemarest.repository.DirectorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    @Autowired
    DirectorRepository directorRepository;


    public HttpEntity saveDirector(Director director) {

        Director save = directorRepository.save(director);
        if (save != null) {
            return new ResponseEntity(new ApiResponse("success", true, save), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.ACCEPTED);
    }


    public HttpEntity getAllDirector() {

        List<Director> directors = directorRepository.findAll();
        if (directors != null) {
            return new ResponseEntity(new ApiResponse("success", true, directors), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.ACCEPTED);
    }


    public HttpEntity editDirector(Integer id, DirectorDto directorDto) {

        Optional<Director> byId = directorRepository.findById(id);
        if (!byId.isPresent()) {

            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.ACCEPTED);
        }
        Director director = byId.get();
        director.setName(directorDto.getName());
        directorRepository.save(director);


        return new ResponseEntity(new ApiResponse("success", true, byId), HttpStatus.ACCEPTED);

    }


    public HttpEntity deleteDirector(Integer id) {

        try {
            directorRepository.deleteById(id);
            return new ResponseEntity(new ApiResponse("success", true), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.ACCEPTED);
        }
    }
}
