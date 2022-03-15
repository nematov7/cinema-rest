package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.entity.Distributor;
import uz.pdp.cinemarest.payload.ApiResponse;
import uz.pdp.cinemarest.payload.DistributorDto;
import uz.pdp.cinemarest.service.DistributorService;

import java.util.List;

@RestController
@RequestMapping("/distributor")
public class DistributorController {
    @Autowired
    DistributorService service;

    @PostMapping("/save")
    public HttpEntity<?> saveDistributor(@RequestBody DistributorDto distributorDto) {
        Distributor distributor = service.saveDistributor(distributorDto);
        if (distributor != null) {
            return new ResponseEntity(new ApiResponse("success",
                    true, distributor), HttpStatus.OK);
        }
        return new ResponseEntity(new ApiResponse("Wrong", false, null), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getDistributor")
    public List<Distributor> getDistributorAll() {
        return service.getDistributorAll();
    }

    @PostMapping("/update/{id}")
    public Distributor updateById(@PathVariable Integer id, @RequestBody DistributorDto distributorDto) {

        return service.updateById(id, distributorDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }
}
