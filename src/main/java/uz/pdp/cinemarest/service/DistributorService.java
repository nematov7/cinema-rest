package uz.pdp.cinemarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarest.entity.Distributor;
import uz.pdp.cinemarest.dto.DistributorDto;
import uz.pdp.cinemarest.repository.DistributorRepository;

import java.util.List;

@Service
public class DistributorService {
    @Autowired
    DistributorRepository repository;


    public Distributor  saveDistributor(DistributorDto distributorDto) {
        if (distributorDto!=null){
            Distributor distributor = new Distributor();
            distributor.setName(distributorDto.getName());
            distributor.setDescription(distributorDto.getDescription());

            return repository.save(distributor);
        }
        return null;
    }

    public List<Distributor> getDistributorAll() {
        return repository.findAll();
    }

    public Distributor updateById(Integer id, DistributorDto distributorDto) {
        Distributor byId =getById(id);
        byId.setDescription(distributorDto.getDescription());
        byId.setName(distributorDto.getName());
       return repository.save(byId);
    }

    private Distributor getById(Integer id) {
          return   repository.getById(id);
    }

    public String  deleteById(Integer id) {
       try {

        repository.deleteById(id);
        return "delete ";
       }catch (Exception e){

        return "not de;ete";
       }

    }
}
