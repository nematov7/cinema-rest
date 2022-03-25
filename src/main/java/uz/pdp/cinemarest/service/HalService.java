package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.HolDto;
import uz.pdp.cinemarest.entity.Hall;
import uz.pdp.cinemarest.projection.CustomHall;
import uz.pdp.cinemarest.projection.CustomMovieById;
import uz.pdp.cinemarest.repository.HalRepository;

import java.util.Optional;

@Service
public class HalService {

    @Autowired
    HalRepository halRepository;


    public Hall saveHal(HolDto holDto) {
        Hall hall = new Hall();

        hall.setName(holDto.getName());
        hall.setVipAdditionalFeeInPercent(holDto.getVipAdditionalFeeInPercent());
        return halRepository.save(hall);
    }

    public HttpEntity getHalById(Integer id) {
        if (!halRepository.findById(id).isPresent()) {
             return new ResponseEntity(new ApiResponse("wrong",false), HttpStatus.BAD_REQUEST)  ;
        }
return new ResponseEntity(new ApiResponse("success",true,halRepository.findById(id).get()),HttpStatus.OK);

    }

    public Hall editHal(HolDto holDto, Integer id) {
        Hall halById = halRepository.getById(id);

        halById.setName(holDto.getName());
        halById.setVipAdditionalFeeInPercent(holDto.getVipAdditionalFeeInPercent());

        return halRepository.save(halById);
    }
    public String  deleteHal(Integer id){
        try {
            halRepository.deleteById(id);
            return "delete";
        }catch (Exception e){
            return "nor delete";
        }
    }
}
