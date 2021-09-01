package ua.metlife.claims.simplecs.repo;

import org.springframework.data.repository.CrudRepository;
import ua.metlife.claims.simplecs.entity.crs.CrsfClm;

public interface CrsfClmRepository extends CrudRepository<CrsfClm, String> {

    String getNextClaimNumber(Integer year);
}
