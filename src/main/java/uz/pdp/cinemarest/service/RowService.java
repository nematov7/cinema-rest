package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.RowDro;
import uz.pdp.cinemarest.entity.Row;
import uz.pdp.cinemarest.projection.CustomRow;
import uz.pdp.cinemarest.repository.RowRepository;

import java.util.List;

@Service
public class RowService {
    @Autowired
    RowRepository rowRepository;


    public HttpEntity<?> getRowsByHallId(Integer id){
        try {
            return new ResponseEntity(new ApiResponse("Success",
                    true,  rowRepository.getRowsByHallId(id)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("wrong",
                    false,  false), HttpStatus.BAD_REQUEST);
        }
    }


    public HttpEntity<?> saveRow(RowDro rowDto, Integer hallId) {
//        Row row=new Row();
//        for (Integer i = 0; i < rowDto.getNumber(); i++) {
//           row.se
//        }
        return null;
    }
}
