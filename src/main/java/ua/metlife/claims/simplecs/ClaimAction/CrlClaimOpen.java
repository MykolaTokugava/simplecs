package ua.metlife.claims.simplecs.ClaimAction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimIntegrator;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneral1c;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneralBank;
import ua.metlife.claims.simplecs.entity.crl.CrlPayment;
import ua.metlife.claims.simplecs.entity.crs.CrsfClm;
import ua.metlife.claims.simplecs.entity.crs.CrsfCrp;
import ua.metlife.claims.simplecs.entity.crs.CrsfPol;
import ua.metlife.claims.simplecs.processing.Config;
import ua.metlife.claims.simplecs.processing.DateTools;
import ua.metlife.claims.simplecs.repo.CrlPaymentRepository;
import ua.metlife.claims.simplecs.repo.CrsfClmRepository;
import ua.metlife.claims.simplecs.repo.CrsfCrpRepository;
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
    private CrsfClmRepository crsfClmRepository;

    @Autowired
    CrsfPolRepository crsfPolRepository;

    @Autowired
    private CrsfCrpRepository crsfCrpRepository;

//    @Autowired
//    CrlGeneral1cRepository crlGeneral1cRepository;
//
//    @Autowired
//    CrlGeneralBankRepository crlGeneralBankRepository;

    /*
    0. Вычитываем данные о клиенте из crl_general_1c
    1. Проверяем наличие у клиента номера полиса (таблица  cs_id_link -> C_POLICY_ID || C_TAXCODE)- >
    если нет присваиваем через nextClaimNumberForClaim и записываем CRSFCRP (from CRSFCLM -> CLMNO) C.yearxxxxxxx
    2. Записываем информацию в CRSFPOL + CrsfClm

    CLMNO " 21.xxxx"
    POLNO "C21.xxxx"

     */

    public void claimOpen(EntityManager entityManager, Integer id) {

        ClaimIntegrator claimIntegrator = new ClaimIntegrator();
        claimIntegrator.setEntityManager(entityManager);
        Connection conn = ConnectionFromJpa.getConnection(claimIntegrator.getEntityManager());
        log.info("ZOPA1");
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

        log.info("search size = " + crlPaymentRepository.findByGeneralIdCustomerFullName("Pedro Gonsales Chmo").size());

        if (crlPayment!=null) {
            log.info("crlPayment ID: " + crlPayment.getId());
            log.info("paidFrom: " + crlPayment.getPaidFrom());
            log.info("paidTo: " + crlPayment.getPaidTo());
            log.info("crlPayment genIcId: " + crlPayment.getGeneral_Id());
        } else {
            log.error("crlPayment is Null for ID: " + id);
            return;
        }
        //CrlGeneral1c crlGeneral1c = crlGeneral1cRepository.findById(crlPayment.getGeneral_Id()).get();
        CrlGeneral1c crlGeneral1c = crlPayment.getGeneralId();

        if (crlGeneral1c!=null) {
            log.info("crlGeneral1c ID: " + crlGeneral1c.getId());
        } else {
            log.error("crlGeneral1c is Null for ID payment: " + crlPayment.getId());
            return;
        }

        //CrlGeneralBank crlGeneralBank = crlGeneralBankRepository.findByRelated1cGenIdData(crlPayment.getGeneral_Id());
        CrlGeneralBank crlGeneralBank = crlPayment.getCrlGeneralBank();

        String address = crlGeneralBank.getCustomerAddress()==null ? "" : crlGeneralBank.getCustomerAddress();
        if (address.length()>30) address = address.substring(0,29);

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
        log.info("Insured passport: " + crlGeneralBank.getInsurantPassport());
        log.info("PremiumCalculated: " + crlPayment.getPremiumCalculated());
        log.info("OWNER_LOGIN(): " + Config.getOWNER_LOGIN());

        String claimStatus = "U";
        String claimDeathStatus = "N";

        //add to CrsfCrp

        CrsfCrp crsfCrp = new CrsfCrp();
        crsfCrp.setPolno(polNumber);
        crsfCrp.setAdrs1(address);
        crsfCrp.setAdrs2("");
        crsfCrp.setCity("");
        crsfCrp.setEffdte(crlPayment.getPaidFrom()!=null ? crlPayment.getPaidFrom() : "");
        crsfCrp.setMatdte(crlPayment.getPaidTo()!=null ? crlPayment.getPaidTo() : "");
        crsfCrp.setOname(crlGeneral1c.getCustomerFullName());
        crsfCrp.setPlob("10");
        crsfCrp.setRecdte(DateTools.getDateNowYmd());
        crsfCrp.setRecusr(Config.getOWNER_LOGIN());
        crsfCrp.setZip("");

        crsfCrpRepository.save(crsfCrp);
        log.info("Claim added to CrsfCrp...");

        //---

        CrsfPol itemPol = new CrsfPol();
        itemPol.setClmno(clmNumber);
        itemPol.setPolno(polNumber);
        itemPol.setCrfid("P");
        itemPol.setStat(claimStatus);
        itemPol.setStatdte(DateTools.getDateNowYmd());
        itemPol.setFaok("N");
        itemPol.setRaok("N");
        itemPol.setBnkasgn("N");
        itemPol.setIname(crlGeneral1c.getCustomerFullName());
        itemPol.setIbthd(crlGeneral1c.getCustomerDateOfBirth());
        itemPol.setIidno(crlGeneralBank.getTaxcode()==null ? "" : crlGeneralBank.getTaxcode());//crlGeneralBank.getInsurantPassport()==null ? "" : crlGeneralBank.getInsurantPassport()
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
        itemPol.setResamt(BigDecimal.ZERO);//crlPayment.getPremiumCalculated()
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
        log.info("Claim added to CrsfPol...");

        crlPayment.getGeneralId().setClaim(1);
        crlPayment.getGeneralId().setClaimDate(DateTools.getDateNowYmd());

        crlPaymentRepository.save(crlPayment);
        log.info("Claim status updated in CrlPayment...");


         //-------------------------------------------
        //TAXCDE ?? / NOTIFY DATE / OCCURED DATE

        CrsfClm crsfClm = new CrsfClm();
        crsfClm.setClmno(clmNumber);
        crsfClm.setCrfid("C");
        crsfClm.setClmon("");
        crsfClm.setStat(claimStatus);
        crsfClm.setStatdte(DateTools.getDateNowYmd());
        crsfClm.setChck("N");
        crsfClm.setFaok("Y");
        crsfClm.setPaok("Y");
        crsfClm.setCateg("");
        crsfClm.setDeath(claimDeathStatus);
        crsfClm.setCause("");
        crsfClm.setEvent("");
        crsfClm.setIname(crlGeneral1c.getCustomerFullName());
        crsfClm.setIbthd(crlGeneral1c.getCustomerDateOfBirth());
        crsfClm.setIidno(crlGeneralBank.getTaxcode()==null ? "" : crlGeneralBank.getTaxcode());
        crsfClm.setIadr1(address);
        crsfClm.setIadr2("");
        crsfClm.setIzip("");
        crsfClm.setIcity("");
        crsfClm.setEvtdt(crlGeneral1c.getClaimDate()==null ? "" : crlGeneral1c.getClaimDate());
        crsfClm.setNtfdt("");
        crsfClm.setEntdt(DateTools.getDateNowYmd());
        crsfClm.setCovamt(crlPayment.getAmount()==null ? BigDecimal.ZERO : crlPayment.getAmount());
        crsfClm.setResamt(BigDecimal.ZERO); //crlPayment.getPremiumCalculated()==null ? BigDecimal.ZERO : crlPayment.getPremiumCalculated()
        crsfClm.setBftamt(new BigDecimal("0.00"));
        crsfClm.setBfttot(new BigDecimal("0.00"));
        crsfClm.setDclobj("");
        crsfClm.setDclp2s(new BigDecimal("0.00"));
        crsfClm.setHcllsqn("0001");
        crsfClm.setSetlsqn("0001");
        crsfClm.setFlwlsqn("0000");
        crsfClm.setFmelsqn("0000");
        crsfClm.setFlelsqn("0000");
        crsfClm.setSetpaid("");
        crsfClm.setSetpdte("");
        crsfClm.setFmeamt(new BigDecimal("0.00"));
        crsfClm.setFleamt(new BigDecimal("0.00"));
        crsfClm.setRecusr(Config.getOWNER_LOGIN());
        crsfClm.setRecdte(DateTools.getDateNowYmd());
        //----

        crsfClmRepository.save(crsfClm);

        log.info("Claim added to CrsfClm...");


    }

    public boolean addClaimToCrsfClm() {



        return true;
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
