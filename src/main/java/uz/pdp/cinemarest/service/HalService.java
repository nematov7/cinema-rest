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

@Service
public class HalService {

    @Autowired
    HallRepository hallRepository;


    public Hall saveHal(HolDto holDto) {
        Hall hall = new Hall();

        hall.setName(holDto.getName());
        hall.setVipAdditionalFeeInPercent(holDto.getVipAdditionalFeeInPercent());
        return hallRepository.save(hall);
    }

    public HttpEntity getHalById(Integer id) {
        if (!hallRepository.findById(id).isPresent()) {
             return new ResponseEntity(new ApiResponse("wrong",false), HttpStatus.BAD_REQUEST)  ;
        }
return new ResponseEntity(new ApiResponse("success",true, hallRepository.findById(id).get()),HttpStatus.OK);

    }

    public Hall editHal(HolDto holDto, Integer id) {
        Hall halById = hallRepository.getById(id);

        halById.setName(holDto.getName());
        halById.setVipAdditionalFeeInPercent(holDto.getVipAdditionalFeeInPercent());

        return hallRepository.save(halById);
    }
    public String  deleteHal(Integer id){
        try {
            hallRepository.deleteById(id);
            return "delete";
        }catch (Exception e){
            return "nor delete";
        }
    }
}
