package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.dto.PriceCategoryDto;
import uz.pdp.cinemarest.entity.PriceCategory;
import uz.pdp.cinemarest.repository.PriceCategoryRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PriceCategoryService {

    @Autowired
    PriceCategoryRepository priceCategoryRepository;


    public HttpEntity<?> savePriceCategory(PriceCategoryDto priceCategoryDto) {
        PriceCategory priceCategory=new PriceCategory();
        try {
            priceCategory.setColor(priceCategoryDto.getColor());
            priceCategory.setAdditionalFeeInPercent(priceCategoryDto.getAdditionalFeeInPercent());
            priceCategory.setName(priceCategoryDto.getName());

            priceCategoryRepository.save(priceCategory);
            return new ResponseEntity(new ApiResponse("Success",
                    true, "save price category"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("wrong",
                    false, false), HttpStatus.BAD_REQUEST);
        }

    }

    public HttpEntity getPriceCategoryById(Integer id) {

        try {
            Optional<PriceCategory> priceCategory = priceCategoryRepository.findById(id);
            return new ResponseEntity(new ApiResponse("Success", true, priceCategory.get()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("wrong",
                    false, false), HttpStatus.BAD_REQUEST);
        }
    }

    public HttpEntity<?> getAllPriceCategory() {

        ArrayList<PriceCategory>priceCategories=new ArrayList<>();

        try {
            for (PriceCategory priceCategory : priceCategoryRepository.findAll()) {
                priceCategories.add(priceCategory);
            }
            return new ResponseEntity(new ApiResponse("Success", true,priceCategories ), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("wrong",
                    false, false), HttpStatus.BAD_REQUEST);
        }
    }

    public HttpEntity<?> editPriceCategory(Integer id, PriceCategoryDto priceCategoryDto) {

            Optional<PriceCategory> priceCategory = priceCategoryRepository.findById(id);
        try {
            priceCategory.get().setName(priceCategoryDto.getName());
            priceCategory.get().setColor(priceCategoryDto.getColor());
            priceCategory.get().setAdditionalFeeInPercent(priceCategoryDto.getAdditionalFeeInPercent());

            PriceCategory save = priceCategoryRepository.save(priceCategory.get());

            return new ResponseEntity(new ApiResponse("Success", true,save ), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("wrong",
                    false, false), HttpStatus.BAD_REQUEST);
        }
    }

    public HttpEntity<?> deletePriceCategory(Integer id) {
        try {
            priceCategoryRepository.deleteById(id);
            return new ResponseEntity(new ApiResponse("Success", true,"deletePriceCategory" ), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(new ApiResponse("wrong",
                    false, false), HttpStatus.BAD_REQUEST);

        }
    }
}
