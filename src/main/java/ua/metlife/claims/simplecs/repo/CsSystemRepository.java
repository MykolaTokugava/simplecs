package ua.metlife.claims.simplecs.repo;

import org.springframework.data.repository.CrudRepository;
import ua.metlife.claims.simplecs.entity.csr.CsSystem;

import java.util.List;

public interface CsSystemRepository extends CrudRepository<CsSystem, Integer>{

    CsSystem findTopByOrderByIdDesc();

    List<CsSystem> getDataTest();
}




