package ua.metlife.claims.simplecs.repo;

import org.springframework.data.repository.CrudRepository;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneralBank;

import java.util.List;

public interface CrlGeneralBankRepository extends CrudRepository<CrlGeneralBank, Integer> {

    CrlGeneralBank findTopByOrderByIdDesc();
    CrlGeneralBank findByRelated1cGenIdData(Integer id);
    List<CrlGeneralBank> findAll();

}
