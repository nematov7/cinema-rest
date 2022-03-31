package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.projection.AvailableSeatProjection;
import uz.pdp.cinemarest.repository.SeatRepository;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatRepository seatRepository;

    public HttpEntity<?> getAllMovieSessions(Integer movieSessionId) {
        if (movieSessionId != null) {
        List<AvailableSeatProjection> availableSeatForSession = seatRepository.getAvailableSeatsByMovieSessionId(movieSessionId);
        return new ResponseEntity(new ApiResponse("success",
                true, availableSeatForSession), HttpStatus.OK);
    } else {
        return new ResponseEntity(new ApiResponse("Wrong",
                false, null), HttpStatus.BAD_REQUEST);
    }


}
}
