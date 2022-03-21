package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.projection.CustomRow;
import uz.pdp.cinemarest.repository.RowRepository;

import java.util.List;

@Service
public class RowService {
    @Autowired
    RowRepository rowRepository;
    public List<CustomRow> getRow(Integer id){
        return rowRepository.getRowsByHallId(id);
    }

}
