package ua.metlife.claims.simplecs.repo;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ua.metlife.claims.simplecs.entity.csr.CsSystem;
@EnableTransactionManagement
@Component
public class CsSystemTransaction {


    @Transactional
    public void doTransaction(CsSystemRepository csSystemRepository) {
        System.out.println("Transaction started...");
        CsSystem item = csSystemRepository.findTopByOrderByIdDesc();

        try {
            CsSystem n = new CsSystem();
            n.setId(item.getId()+1);
            n.setSystemName("test1");
            n.setSystemInfo("test-info");
            n.setSystemType("system");
            n.setSystemCode("CRL2");
            csSystemRepository.save(n);
        } catch ( Exception e) {
            System.err.println("Error:" + e.getMessage());
        }
        System.out.println("Transaction finished.");

    }

}
