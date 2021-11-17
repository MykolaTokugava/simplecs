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

    @Query("SELECT p FROM CrlPayment p WHERE LOWER(p.generalId.customerFullName) LIKE %:name%")
    List<CrlPayment> findByGName(@Param("name") String name);


    @Query("SELECT p FROM CrlPayment p WHERE LOWER(p.generalId.customerFullName) LIKE %:name% OR p.generalId.taxcode=:taxcode")
    List<CrlPayment> findByGNameTaxcode(@Param("name") String name, @Param("taxcode") String taxcode);

    @Query("SELECT p FROM CrlPayment p WHERE p.generalId.taxcode=:taxcode")
    List<CrlPayment> findByTaxcode(@Param("taxcode") String taxcode);

    @Query("SELECT p FROM CrlPayment p WHERE p.generalId.claim=1 order by p.generalId.id")
    List<CrlPayment> findTop20WithStatusClaim();

}
