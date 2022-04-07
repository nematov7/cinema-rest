package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.SeatDto;
import uz.pdp.cinemarest.entity.Row;
import uz.pdp.cinemarest.entity.Seat;
import uz.pdp.cinemarest.projection.AvailableSeatProjection;
import uz.pdp.cinemarest.repository.RowRepository;
import uz.pdp.cinemarest.repository.SeatRepository;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    RowRepository rowRepository;

    public HttpEntity<?> getAllMovieSessions(Integer movieSessionId) {
        if (movieSessionId != null) {
            List<AvailableSeatProjection> availableSeatForSession = seatRepository.getAvailableSeatsByMovieSessionId(movieSessionId);
            return new ResponseEntity(new ApiResponse("success",
                    true, availableSeatForSession), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ApiResponse("Wrong", false, null), HttpStatus.BAD_REQUEST);
        }


    }

    public HttpEntity<?> saveSeat(SeatDto seatDto, Integer hallId) {
        try {
            int seatQuantity = seatDto.getSeatQuantity();//10
            int rowLastNumber = seatDto.getRowNumberDan();//1
            int rowQuantity = Math.abs(seatDto.getRowNumbeGacha() - seatDto.getRowNumberDan());

            for (int i = 0; i <= rowQuantity; i++) {
                int rowNumber = i + rowLastNumber;
                Row row = rowRepository.getRowByRowNumber(rowNumber, hallId);

                for (int e = 1; e <= seatQuantity; e++) {
                    Seat seat = new Seat();

                    seat.setRow(row);
                    seat.setNumber(e);
                    seatRepository.save(seat);
                }
                if (seatDto.getRowNumbeGacha()==0){
                    break;
                }
            }
            return new ResponseEntity(new ApiResponse("success", true, "save seat"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("Wrong", false, null), HttpStatus.BAD_REQUEST);

        }
    }
}
