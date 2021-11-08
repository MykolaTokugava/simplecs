package ua.metlife.claims.simplecs.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.metlife.claims.simplecs.entity.crl.CrlPayment;

import java.util.List;

public interface CrlPaymentRepository extends CrudRepository<CrlPayment, Integer> {

    CrlPayment findTopByOrderByIdDesc();

    List<CrlPayment> findAll();

    List<CrlPayment> findTop20ByOrderByIdDesc();

    //@Query("SELECT с FROM CrlPayment p WHERE p.general_Id.id = p.general_Id AND p.generalId.customerFullName = :name")

    List<CrlPayment> findByGeneralIdCustomerFullName(@Param("name") String name);

    @Query("SELECT p FROM CrlPayment p WHERE p.generalId.customerFullName LIKE %:name%")
    List<CrlPayment> findByGName(@Param("name") String name);



}
