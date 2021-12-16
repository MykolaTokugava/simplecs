package ua.metlife.claims.simplecs.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneral1c;

import java.util.List;

public interface CrlGeneral1cRepository extends CrudRepository<CrlGeneral1c, Integer> {

    CrlGeneral1c findTopByOrderByIdDesc();
    List<CrlGeneral1c> findAll();

    @Query("SELECT p FROM CrlGeneral1c p WHERE p.taxcode=:taxcode")
    List<CrlGeneral1c> findByTaxcode(@Param("taxcode") String taxcode);

}
