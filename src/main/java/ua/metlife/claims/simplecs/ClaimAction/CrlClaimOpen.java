package ua.metlife.claims.simplecs.ClaimAction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimIntegrator;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneral1c;
import ua.metlife.claims.simplecs.entity.crl.CrlPayment;
import ua.metlife.claims.simplecs.entity.crs.CrsfPol;
import ua.metlife.claims.simplecs.processing.Config;
import ua.metlife.claims.simplecs.processing.DateTools;
import ua.metlife.claims.simplecs.repo.CrlPaymentRepository;
import ua.metlife.claims.simplecs.utils.ClaimSystemLink;
import ua.metlife.claims.simplecs.utils.ConnectionFromJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.sql.Connection;

@Slf4j
@Component
public class CrlClaimOpen {

    @Autowired
    private CrlPaymentRepository crlPaymentRepository;

    /*
    0. Вычитываем данные о клиенте из crl_general_1c
    1. Проверяем наличие у клиента номера полиса (таблица  cs_id_link -> C_POLICY_ID || C_TAXCODE)- >
    если нет присваиваем через nextClaimNumberForClaim и записываем CRSFCRP (from CRSFCLM -> CLMNO) C.yearxxxxxxx
    2. Записываем информацию в CRSFPOL

    CLMNO " 21.xxxx"
    POLNO "C21.xxxx"

     */

    public void claimOpen(EntityManager entityManager, Integer id) {

        ClaimIntegrator claimIntegrator = new ClaimIntegrator();
        claimIntegrator.setEntityManager(entityManager);
        Connection conn = ConnectionFromJpa.getConnection(claimIntegrator.getEntityManager());
        String clmNumber = ClaimSystemLink.nextClaimNumberForClaim(conn, null);
        log.info("clmNumber CRSFCLM: " + clmNumber);
        String polNumber = ClaimSystemLink.nextClaimNumberForCrl(conn, null);
        log.info("polNumber CRSFCRP: " + polNumber);

        CrlPayment crlPayment = crlPaymentRepository.findTopByOrderByIdDesc();
        if (crlPayment == null) return;
        CrlGeneral1c crlGeneral1c = crlPayment.getGeneralId();


        CrsfPol itemPol = new CrsfPol();
        itemPol.setClmno(clmNumber);
        itemPol.setPolno(polNumber);
        itemPol.setCrfid("P");
        itemPol.setStat("U");
        itemPol.setStatdte(DateTools.getDateNowYmd());
        itemPol.setFaok("N");
        itemPol.setRaok("N");
        itemPol.setBnkasgn("N");
        itemPol.setIname(crlGeneral1c.getCustomerFullName());
        itemPol.setIbthd(crlGeneral1c.getCustomerDateOfBirth());
        itemPol.setIidno(crlPayment.getCrlGeneralBank().getInsurantPassport());
        itemPol.setOname(crlGeneral1c.getCustomerFullName());
        itemPol.setObthd(crlGeneral1c.getCustomerDateOfBirth());
        itemPol.setCpyfr("O");
        itemPol.setType("I");
        itemPol.setPlob("10");
        itemPol.setIssdte(crlPayment.getPaidFrom());
        itemPol.setEffdte(crlPayment.getPaidTo());
        itemPol.setPaydte(crlPayment.getDate1c());
        itemPol.setCastat("");//crlGeneral1c.getStatus()
        itemPol.setBilmod("00`");//crlGeneral1c.getModality()
        itemPol.setModprm(new BigDecimal("0.00"));
        itemPol.setAnnprm(new BigDecimal("0.00"));
        itemPol.setWcpprm(new BigDecimal("0.00"));
        itemPol.setCovamt(crlPayment.getAmount());
        itemPol.setResamt(crlPayment.getPremiumCalculated());
        itemPol.setBftamt(new BigDecimal("0.00"));
        itemPol.setBfttot(new BigDecimal("0.00"));
        itemPol.setCcag1fc("");
        itemPol.setEaag1nm("");
        itemPol.setCcag2fc("");
        itemPol.setEaag2nm("");
        itemPol.setDclobj("");
        itemPol.setDclp2s(BigDecimal.ZERO);
        itemPol.setRecusr(Config.getOWNER_LOGIN());
        itemPol.setRecdte(DateTools.getDateNowYmd());

        EntityManager em = claimIntegrator.getEntityManager(); //R2D2
        EntityTransaction newTr = em.getTransaction();

        try {
            newTr.begin();
            em.persist(itemPol);
            em.flush();
            newTr.commit();
        } catch (Exception ex) {
            if (newTr != null && newTr.isActive()) {
                newTr.rollback();
            }
            log.error("Exception!: " + ex.getMessage());
        } finally {
            newTr = null;
            log.info("Claim Open - Done...");
        }

    }


}
