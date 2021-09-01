package ua.metlife.claims.simplecs.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.metlife.claims.simplecs.entity.csr.CsSystem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CsSystemRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    CsSystemRepository csSystemRepository;

    @SuppressWarnings("unused")
    public List<CsSystem> getDataTest() {
        List<CsSystem> items = entityManager.createNamedQuery("CsSystem.findAll").getResultList();
        // GCompany gCompany = (GCompany) EMF.getEM().createNamedQuery("GCompany.findByCompanyId").setParameter("companyId", 2).getResultList().get(0);
        items.forEach(csSystem -> System.out.println("code:" + csSystem.getSystemCode()));
        System.out.println("res = " + items.size());
        return items;
    }

}