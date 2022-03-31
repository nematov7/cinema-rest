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

    @PostMapping()
    public String saveSeat(SeatDto seatDto) {
        return null;   // TODO: 28.03.2022
    }


    @GetMapping("showAvailableSeat/{movieSessionId}")
    public HttpEntity getAllMovieSessions(@PathVariable Integer movieSessionId) {
        return seatService.getAllMovieSessions(movieSessionId);
    }
}
