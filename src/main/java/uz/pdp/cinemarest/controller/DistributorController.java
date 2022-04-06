package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.DistributorDto;
import uz.pdp.cinemarest.entity.Distributor;
import uz.pdp.cinemarest.service.DistributorService;

import java.util.List;

@RestController
@RequestMapping("/distributor")
public class DistributorController {
    @Autowired
    DistributorService service;

    @GetMapping
    public List<Distributor> getDistributorAll() {
        return service.getDistributorAll();
    }


    @PostMapping
    public HttpEntity<?> saveDistributor(@RequestBody DistributorDto distributorDto) {
        return  service.saveDistributor(distributorDto);
    }

    @PutMapping("/{id}")
    public Distributor updateById(@PathVariable Integer id, @RequestBody DistributorDto distributorDto) {
        return service.updateById(id, distributorDto);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }
}
