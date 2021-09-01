package ua.metlife.claims.simplecs.repo;

import ua.metlife.claims.simplecs.entity.crs.CrsfClm;
import ua.metlife.claims.simplecs.processing.DateTools;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CrsfClmRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    @SuppressWarnings("unused")
    public String getNextClaimNumber(Integer year) {

        if (year == null) {
            year = Integer.parseInt(String.valueOf(DateTools.getCurrentYear().toString().substring(2, 4)));
        } else {
            year = Integer.parseInt(String.valueOf(year.toString().substring(2, 4)));
        }

        List<CrsfClm> items = entityManager.createNamedQuery("CrsfClm.findLastClaimNumberForYear").setParameter("year", year.toString()).getResultList();
        items.forEach(item -> System.out.println("number:" + item.getClmno()));
        System.out.println("res =>= " + items.size());

//        try {
//            entityManager.getTransaction().begin();
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//        }


        return "";
    }

}

