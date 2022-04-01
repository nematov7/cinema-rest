package uz.pdp.cinemarest.controller;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.service.TicketService;

import java.io.IOException;

@RestController
@RequestMapping("/addToCart")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/{userId}")
    public HttpEntity<?> addTicket(@RequestParam("movieSessionId") Integer movieSessionId,
                                   @RequestParam("seatId")Integer seatId, @PathVariable Integer userId) throws IOException, WriterException {
        return ticketService.generationTicket(movieSessionId,seatId,userId);
    }
    @GetMapping("/{ticketId}")
    public HttpEntity<?> getTicket(@PathVariable Integer ticketId){

     return    ticketService.getTicketById(ticketId);

    }
}
