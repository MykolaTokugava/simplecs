package ua.metlife.claims.simplecs.ClaimAction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimIntegrator;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneral1c;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneralBank;
import ua.metlife.claims.simplecs.entity.crl.CrlPayment;
import ua.metlife.claims.simplecs.entity.crs.CrsfPol;
import ua.metlife.claims.simplecs.processing.Config;
import ua.metlife.claims.simplecs.processing.DateTools;
import ua.metlife.claims.simplecs.repo.CrlGeneral1cRepository;
import ua.metlife.claims.simplecs.repo.CrlGeneralBankRepository;
import ua.metlife.claims.simplecs.repo.CrlPaymentRepository;
import ua.metlife.claims.simplecs.repo.CrsfPolRepository;
import ua.metlife.claims.simplecs.utils.ClaimSystemLink;
import ua.metlife.claims.simplecs.utils.ConnectionFromJpa;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Connection;

@Slf4j
@Component
@Transactional
public class CrlClaimOpen {

    @Autowired
    private CrlPaymentRepository crlPaymentRepository;

    @Autowired
    CrlGeneral1cRepository crlGeneral1cRepository;

    @Autowired
    CrlGeneralBankRepository crlGeneralBankRepository;

    @Autowired
    CrsfPolRepository crsfPolRepository;

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
        CrlPayment crlPayment = null;
        if (id!=null) {
            log.info("queryID: " +id );
            try {
                crlPayment = crlPaymentRepository.findById(id).get();
                log.info(">> " + crlPayment.getId());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            log.info("queryID is null");
            crlPayment = crlPaymentRepository.findTopByOrderByIdDesc();
        }
        if (crlPayment!=null) {
            log.info("crlPayment ID: " + crlPayment.getId());
            log.info("paidFrom: " + crlPayment.getPaidFrom());
            log.info("paidTo: " + crlPayment.getPaidTo());
            log.info("crlPayment genIcId: " + crlPayment.getGeneral_Id());
        } else {
            log.error("crlPayment is Null for ID: " + id);
            return;
        }
        CrlGeneral1c crlGeneral1c = crlGeneral1cRepository.findById(crlPayment.getGeneral_Id()).get();

        if (crlGeneral1c!=null) {
            log.info("crlGeneral1c ID: " + crlGeneral1c.getId());
        } else {
            log.error("crlGeneral1c is Null for ID payment: " + crlPayment.getId());
            return;
        }

        CrlGeneralBank crlGeneralBank = crlGeneralBankRepository.findByRelated1cGenIdData(crlPayment.getGeneral_Id());

        if (crlGeneralBank!=null) {
            log.info("crlGeneralBank ID: " + crlGeneralBank.getId());
        } else {
            log.error("crlGeneralBank is Null for ID payment: " + crlPayment.getId());
            return;
        }

        log.info("clmNumber: " +clmNumber);
        log.info("polNumber: " +polNumber);
        log.info("Insured Name: " +crlGeneral1c.getCustomerFullName());
        log.info("Insured DOB: " +crlGeneral1c.getCustomerDateOfBirth());
        log.info("Insured passport: " +crlGeneralBank.getInsurantPassport());
        log.info("PremiumCalculated: " +crlPayment.getPremiumCalculated());


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
        itemPol.setIidno(crlGeneralBank.getInsurantPassport());
        itemPol.setOname(crlGeneral1c.getCustomerFullName());
        itemPol.setObthd(crlGeneral1c.getCustomerDateOfBirth());
        itemPol.setCpyfr("O");
        itemPol.setType("I");
        itemPol.setPlob("10");
        itemPol.setIssdte(crlPayment.getPaidFrom());
        itemPol.setEffdte(crlPayment.getPaidTo());
        itemPol.setPaydte(crlPayment.getDate1c());
        itemPol.setCastat("");//crlGeneral1c.getStatus()
        itemPol.setBilmod("00");//crlGeneral1c.getModality()
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
        itemPol.setClmon("");

//        EntityManager em = claimIntegrator.getEntityManager();
//        EntityTransaction newTr = em.getTransaction();

//        try {
//            newTr.begin();
//            em.persist(itemPol);
//            em.flush();
//            newTr.commit();
//        } catch (Exception ex) {
//            if (newTr != null && newTr.isActive()) {
//                newTr.rollback();
//            }
//            log.error("Exception!: " + ex.getMessage());
//        } finally {
//            newTr = null;
//            log.info("Claim Open - Done...");
//        }

//        if (!ObjectUtils.isEmpty(itemPol) && !entityManager.contains(itemPol)) {
//            entityManager.persist(itemPol);
//            entityManager.flush();
//        }

        crsfPolRepository.save(itemPol);
        log.info("Claim added...");


    }

//    @Transactional
//    public String insertUpdate(CrsfPol item, CrsfPolRepository crsfPolRepository) {
//        CrsfPol save = crsfPolRepository.save(item); // Call to the Repository
//
//        try {
//            throw new Exception("Error");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (save != null) {
//            return "The Student is successfully inserted within the database";
//        }
//        return "Failed to insert Student within the database";
//    }



}
