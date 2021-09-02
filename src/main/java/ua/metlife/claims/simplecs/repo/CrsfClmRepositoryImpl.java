package ua.metlife.claims.simplecs.repo;

import ua.metlife.claims.simplecs.processing.DateTools;
import ua.metlife.claims.simplecs.utils.ClaimSystemLink;
import ua.metlife.claims.simplecs.utils.ConnectionFromJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;

public class CrsfClmRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    @SuppressWarnings("unused")
    public String getNextClaimNumber(Integer year) {

        Connection conn = ConnectionFromJpa.getConnection(entityManager);

        new ClaimSystemLink().addToCRSFCLM(conn);

        if (year == null) {
            year = Integer.parseInt(String.valueOf(DateTools.getCurrentYear().toString().substring(2, 4)));
        } else {
            year = Integer.parseInt(String.valueOf(year.toString().substring(2, 4)));
        }

        //List<CrsfClm> items = entityManager.createNamedQuery("CrsfClm.findLastClaimNumberForYear").setParameter("year", year.toString()).getResultList();
        //System.out.println("res =>= " + items.size());
        //items.forEach(item -> System.out.println("number:" + item.getIname()));

        ClaimSystemLink.nextClaimNumber(conn, 2021);


        try {
            entityManager.getTransaction().begin();
//            entityManager.getTransaction().commit();
        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
        }




        return "";
    }

}

