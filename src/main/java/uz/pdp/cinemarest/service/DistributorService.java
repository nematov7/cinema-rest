package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.dto.ApiResponse;
import uz.pdp.cinemarest.entity.Distributor;
import uz.pdp.cinemarest.dto.DistributorDto;
import uz.pdp.cinemarest.repository.DistributorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributorService {
    @Autowired
    DistributorRepository repository;


    public HttpEntity saveDistributor(DistributorDto distributorDto) {
        if (distributorDto != null) {
            Distributor distributor = new Distributor();
            distributor.setName(distributorDto.getName());
            distributor.setDescription(distributorDto.getDescription());
            Distributor distributor1 = repository.save(distributor);
            return new ResponseEntity(new ApiResponse("success",
                    true, distributor1), HttpStatus.OK);
        }
          return new ResponseEntity(new ApiResponse("Wrong", false, null), HttpStatus.BAD_REQUEST);

    }

    public List<Distributor> getDistributorAll() {
        List<Distributor> distributorList = new ArrayList<>();
        Distributor distributor1 = new Distributor();
        for (Distributor distributor : repository.findAll()) {
            distributor1.setName(distributor.getName());
            distributor1.setDescription(distributor.getDescription());
            distributor1.setId(distributor.getId());
            distributorList.add(distributor1);
        }

        return distributorList;
    }

    public Distributor updateById(Integer id, DistributorDto distributorDto) {
        Distributor byId = getById(id);
        byId.setDescription(distributorDto.getDescription());
        byId.setName(distributorDto.getName());
        return repository.save(byId);
    }

    private Distributor getById(Integer id) {
        return repository.getById(id);
    }

    public String deleteById(Integer id) {
        try {

            repository.deleteById(id);
            return "delete ";
        } catch (Exception e) {

            return "not de;ete";
        }

    }
}
