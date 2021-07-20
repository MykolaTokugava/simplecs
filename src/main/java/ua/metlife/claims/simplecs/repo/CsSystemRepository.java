package ua.metlife.claims.simplecs.repo;

import org.springframework.data.repository.CrudRepository;
import ua.metlife.claims.simplecs.entity.csr.CsSystem;

public interface  CsSystemRepository extends CrudRepository<CsSystem, Integer>{

    CsSystem findTopByOrderByIdDesc();
}




