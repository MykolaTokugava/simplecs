package ua.metlife.claims.simplecs.repo;

import org.springframework.data.repository.CrudRepository;
import ua.metlife.claims.simplecs.entity.crl.CrlPayment;

import java.util.List;

public interface CrlPaymentRepository extends CrudRepository<CrlPayment, Integer> {

    CrlPayment findTopByOrderByIdDesc();
    List<CrlPayment> findAll();
}
