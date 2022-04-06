package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.HolDto;
import uz.pdp.cinemarest.entity.Hall;
import uz.pdp.cinemarest.repository.HallRepository;

import java.util.ArrayList;

@Service
public class HalService {

    @Autowired
    HallRepository hallRepository;


    public HttpEntity<?> saveHal(HolDto holDto) {
        try {
            Hall hall = new Hall();
            hall.setName(holDto.getName());
            hall.setVipAdditionalFeeInPercent(holDto.getVipAdditionalFeeInPercent());
            hallRepository.save(hall);
            return new ResponseEntity(new ApiResponse("success", true, hall), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.BAD_REQUEST);
        }
    }

    public HttpEntity getHalById(Integer id) {
        if (!hallRepository.findById(id).isPresent()) {
            return new ResponseEntity(new ApiResponse("wrong", false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new ApiResponse("success", true, hallRepository.findById(id).get()), HttpStatus.OK);

    }

    public HttpEntity<?> editHal(HolDto holDto, Integer id) {
        Hall halById = hallRepository.getById(id);
        halById.setName(holDto.getName());
        halById.setVipAdditionalFeeInPercent(holDto.getVipAdditionalFeeInPercent());

        Hall hall = hallRepository.save(halById);

        if (hall != null) {
            return new ResponseEntity(new ApiResponse("success", true, hall), HttpStatus.OK);
        }
        return new ResponseEntity(new ApiResponse("wrong", false, null), HttpStatus.BAD_REQUEST);
    }


    public HttpEntity<?> deleteHal(Integer id) {
        try {
            hallRepository.deleteById(id);
            return new ResponseEntity(new ApiResponse("success", true, "delete"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse("wrong", false, null), HttpStatus.BAD_REQUEST);

        }
    }

    public HttpEntity<?> getAllHall() {
        try {
            ArrayList<Hall>halls=new ArrayList<>();

            for (Hall hall : hallRepository.findAll()) {
                halls.add(hall);
            }
            return new ResponseEntity(new ApiResponse("success", true, halls), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("wrong", false, null), HttpStatus.BAD_REQUEST);

        }
    }
}
