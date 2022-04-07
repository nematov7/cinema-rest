package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.dto.SeatDto;
import uz.pdp.cinemarest.service.SeatService;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    SeatService seatService;

    @PostMapping("/{hallId}")
    public HttpEntity<?> saveSeat(@RequestBody  SeatDto seatDto,@PathVariable Integer hallId) {
       return seatService.saveSeat(seatDto,hallId);
    }


    @GetMapping("/{movieSessionId}")
    public HttpEntity getAllMovieSessions(@PathVariable Integer movieSessionId) {
        return seatService.getAllMovieSessions(movieSessionId);
    }

    @PostMapping("/{hallId}")
    public HttpEntity<?> savePriceCategorySeat()
}
