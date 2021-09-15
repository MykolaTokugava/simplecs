package ua.metlife.claims.simplecs.utils;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DbEnvData {

    @PersistenceContext
    private EntityManager entityManager;


//    @PersistenceUnit(unitName = "readwrite.config")
//    private EntityManagerFactory entityManagerFactory;



    public EntityManager getEntityManager() {
        //return entityManagerFactory.createEntityManager();
        return entityManager;
    }

}
