package uz.pdp.cinemarest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarest.dto.SeatDto;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @PostMapping()
    public String  saveSeat(SeatDto seatDto){
      return null;   // TODO: 28.03.2022  
    }
}
