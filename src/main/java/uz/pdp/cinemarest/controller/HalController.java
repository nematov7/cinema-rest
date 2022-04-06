package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.HolDto;
import uz.pdp.cinemarest.entity.Hall;
import uz.pdp.cinemarest.service.HalService;


@RestController
@RequestMapping("/hall")
public class HalController {
    @Autowired
    HalService halService;

    @GetMapping
    public HttpEntity<?> getAllHall() {
        return halService.getAllHall();
    }

    @GetMapping(path = "/{id}")
    public HttpEntity<?> getHallById(@PathVariable Integer id) {
        return halService.getHalById(id);
    }


    @PostMapping
    public HttpEntity<?> saveHall(@RequestBody HolDto holDto) {
      return halService.saveHal(holDto);
    }


    @PutMapping(path = "/{id}")
    public HttpEntity<?> editHal(@RequestBody HolDto holDto, @PathVariable Integer id) {
     return halService.editHal(holDto, id);
    }


    @DeleteMapping(path = "/{id}")
    public HttpEntity<?> deleteHal(@PathVariable Integer id) {
        return halService.deleteHal(id);
    }
}


