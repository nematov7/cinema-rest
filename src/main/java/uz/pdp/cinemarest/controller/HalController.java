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

    @GetMapping(path = "/{id}")
    public HttpEntity<?> getHallById(@PathVariable Integer id) {
        return halService.getHalById(id);
    }



    @PostMapping("/save")
    public ResponseEntity saveHall(HolDto holDto) {

        Hall hall = halService.saveHal(holDto);

        if (hall != null) {
            return new ResponseEntity(new ApiResponse("success", true, hall), HttpStatus.OK);
        }
        return new ResponseEntity(new ApiResponse("wrong", false, null), HttpStatus.BAD_REQUEST);
    }



    @PutMapping(path = "/{id}")
    public ResponseEntity editHal(HolDto holDto, @PathVariable Integer id) {

        Hall hall = halService.editHal(holDto, id);

        if (hall != null) {
            return new ResponseEntity(new ApiResponse("success", true, hall), HttpStatus.OK);
        }
        return new ResponseEntity(new ApiResponse("wrong", false, null), HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteHal(@PathVariable Integer id) {
        return halService.deleteHal(id);
    }
}


