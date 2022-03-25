package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.projection.CustomRow;
import uz.pdp.cinemarest.service.RowService;

import java.util.List;

@RestController
@RequestMapping("/row")
public class RowController {
    @Autowired
    RowService rowService;

    @GetMapping("/{hallId}")
    public HttpEntity<?> getRowsByHallId(@PathVariable Integer hallId) {
        List<CustomRow> row = rowService.getRow(hallId);
        return new ResponseEntity(new ApiResponse("success", true, row), HttpStatus.OK);
    }
}
