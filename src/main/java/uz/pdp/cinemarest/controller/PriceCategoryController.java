package uz.pdp.cinemarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarest.dto.PriceCategoryDto;
import uz.pdp.cinemarest.service.PriceCategoryService;

@RestController
@RequestMapping("/price-category")
public class PriceCategoryController {

    @Autowired
    PriceCategoryService priceCategoryService;

    @PostMapping
    public HttpEntity<?> savePriceCategory(@RequestBody PriceCategoryDto priceCategoryDto) {
        return priceCategoryService.savePriceCategory(priceCategoryDto);
    }


    @GetMapping("/{id}")
    public HttpEntity getPriceCategoryById(@PathVariable Integer id){
      return   priceCategoryService.getPriceCategoryById(id);
    }

    @GetMapping
    public HttpEntity<?> getAllPriceCategory(){
        return priceCategoryService.getAllPriceCategory();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editPriceCategory(@PathVariable Integer id,@RequestBody PriceCategoryDto priceCategoryDto){
        return priceCategoryService.editPriceCategory(id,priceCategoryDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?>deletePriceCategory(@PathVariable Integer id){
      return   priceCategoryService.deletePriceCategory(id);
    }
}
