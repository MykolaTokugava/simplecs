package ua.metlife.claims.simplecs.repo;

import org.springframework.data.repository.CrudRepository;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneral1c;

import java.util.List;

public interface CrlGeneral1cRepository extends CrudRepository<CrlGeneral1c, Integer> {

    CrlGeneral1c findTopByOrderByIdDesc();
    List<CrlGeneral1c> findAll();
}
