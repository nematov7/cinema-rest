package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.RowDro;
import uz.pdp.cinemarest.entity.Hall;
import uz.pdp.cinemarest.entity.Row;
import uz.pdp.cinemarest.projection.CustomRow;
import uz.pdp.cinemarest.repository.HallRepository;
import uz.pdp.cinemarest.repository.RowRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RowService {
    @Autowired
    RowRepository rowRepository;
    @Autowired
    HallRepository hallRepository;


    public HttpEntity<?> getRowsByHallId(Integer id) {
        try {
            return new ResponseEntity(new ApiResponse("Success",
                    true, rowRepository.getRowsByHallId(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong",
                    false, false), HttpStatus.BAD_REQUEST);
        }
    }


    public HttpEntity<?> saveRow(RowDro rowDto, Integer hallId) {
        Optional<Hall> hall = hallRepository.findById(hallId);


            int rowNumber = rowRepository.getLastRowNumber(hallId);

        try {


            for (int i = 1; i <= rowDto.getNumber(); i++) {

                Row row = new Row();

                row.setHall(hall.get());
                row.setNumber(rowNumber + i);
                rowRepository.save(row);
            }
            return new ResponseEntity(new ApiResponse("Success", true, "Row save"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong", false, false), HttpStatus.BAD_REQUEST);
        }
    }
}
