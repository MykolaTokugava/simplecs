/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimAction;


import lombok.extern.slf4j.Slf4j;
import ua.metlife.claims.simplecs.ClaimsEntity.*;
import ua.metlife.claims.simplecs.entity.crs.*;
import ua.metlife.claims.simplecs.processing.Config;
import ua.metlife.claims.simplecs.processing.Convert;
import ua.metlife.claims.simplecs.processing.DateTools;
import ua.metlife.claims.simplecs.utils.ClaimsUtils;
import ua.metlife.claims.simplecs.utils.DbConfigCrl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author NPopov
 */
@Slf4j
public class ClaimOpen {

    private final static String eibPlancode = "*R-EIB    ";

    public ClaimOpen(ClaimIntegrator claimIntegrator) {

        log.info("Claim Open action...");
        String clmNumber = ClaimsUtils.getNewClaimNumberOlas();
        log.info("clmNumber = " + clmNumber);

        GeneralClaimData cgData = claimIntegrator.getGeneralClaimData();
        cgData.setClaimPolicyNumber(clmNumber);

        String CAUSE = "";
        String EVENT = "";

        CAUSE = cgData.getClaimCause() == null ? "" : cgData.getClaimCause();
        EVENT = cgData.getClaimReason() == null ? "" : cgData.getClaimReason();

        String lastHistoryNumber = ClaimsUtils.getLastHistoryNumber(clmNumber);
        log.info("lastHistoryNumber = " + lastHistoryNumber);
        ClaimPolicy сlaimPolicyMain = claimIntegrator.getMainPolicy().getClaimPolicy();
        BigDecimal coverage = ClaimsUtils.calculateTotalCoverage(claimIntegrator.getGeneralClaimData().getClaimsPlanCodes());//??
        ClaimPropertyObject claimPropertyObject = new ClaimPropertyObject();
        claimPropertyObject.setClaimPreviousDate(DateTools.getDateNowYmd());
        cgData.setClaimPropertyObject(claimPropertyObject);
        List<CrsfRsb> crsfRsbList = new ArrayList();
        List<CrsfRsbHi> crsfRsbHiList = new ArrayList();
        List<ClaimPlanCode> toBase = new ArrayList();
        List<CrsfPol> crsfPolList = new ArrayList();
        ClaimPlanCode itemLast = new ClaimPlanCode();
        for (ClaimPlanCode planCode : claimIntegrator.getGeneralClaimData().getClaimsPlanCodes()) {
            toBase.add(planCode);
            itemLast = planCode;
        }

        BigDecimal totalPaid = ClaimsUtils.calculateTotalPaid(claimIntegrator.getGeneralClaimData().getClaimsPlanCodes());

        if (cgData.getClaimStatus().equals("U")) {
            claimPropertyObject.setClaimPreviousStatus("U");
            claimPropertyObject.setClaimStatus("U");
            claimPropertyObject.setClaimFAOKStatus("N");
            claimPropertyObject.setClaimPAOKStatus("N");
            claimPropertyObject.setClaimRAOKStatus("N");
            claimPropertyObject.setClaimHistoryHCLLSQN("0000");
            claimPropertyObject.setClaimHistoryFLWLSQN("0000");
            claimPropertyObject.setClaimHistoryFLELSQN("0000");

        } else if (cgData.getClaimStatus().equals("O1")) {
            claimPropertyObject.setClaimPreviousStatus("U");
            claimPropertyObject.setClaimStatus("O1");
            claimPropertyObject.setClaimFAOKStatus("Y");
            claimPropertyObject.setClaimPAOKStatus("Y");
            claimPropertyObject.setClaimRAOKStatus("Y");
            claimPropertyObject.setClaimHistoryHCLLSQN("0001");
            claimPropertyObject.setClaimHistoryFLWLSQN("0001");
            claimPropertyObject.setClaimHistoryFLELSQN("0000");
        }
        // CrsfPol

        for (ClaimCollector policyCollector : claimIntegrator.getClaimCollectorList()) {
            ClaimPolicy claimPolicyItem = policyCollector.getClaimPolicy();

            CrsfPol itemPol = new CrsfPol();
            itemPol.setClmno(cgData.getClaimPolicyNumber());
            itemPol.setPolno(claimPolicyItem.getPolicyNumberString());
            itemPol.setCrfid("P");
            itemPol.setStat(cgData.getClaimStatus());
            itemPol.setStatdte(DateTools.getDateNowYmd());
            itemPol.setFaok(claimPropertyObject.getClaimFAOKStatus());
            itemPol.setRaok(claimPropertyObject.getClaimRAOKStatus());
            itemPol.setBnkasgn("N");
            itemPol.setIname(claimPolicyItem.getInsured().getNameIni());
            itemPol.setIbthd(Convert.dateToStringWithFormat(claimPolicyItem.getInsured().getDob(), Convert.DATE_FORMAT_BASE));
            itemPol.setIidno(claimPolicyItem.getInsured().getPassport());
            itemPol.setOname(claimPolicyItem.getPayer().getNameIni());
            itemPol.setObthd(Convert.dateToStringWithFormat(claimPolicyItem.getPayer().getDob(), Convert.DATE_FORMAT_BASE));
            itemPol.setCpyfr("O");
            itemPol.setType("I");
            itemPol.setPlob("10");
            itemPol.setIssdte(Convert.dateToStringWithFormat(claimPolicyItem.getIssueDate(), Convert.DATE_FORMAT_BASE));
            itemPol.setEffdte(Convert.dateToStringWithFormat(claimPolicyItem.getMaturityDate(), Convert.DATE_FORMAT_BASE));
            itemPol.setPaydte(Convert.dateToStringWithFormat(claimPolicyItem.getPaidDate(), Convert.DATE_FORMAT_BASE));
            itemPol.setCastat(claimPolicyItem.getStatus());
            itemPol.setBilmod(claimPolicyItem.getModality());
            itemPol.setModprm(claimPolicyItem.getPremiumModality());
            itemPol.setAnnprm(claimPolicyItem.getPremiumYear());
            itemPol.setWcpprm(new BigDecimal("0.00"));
            itemPol.setCovamt(coverage);
            itemPol.setResamt(totalPaid);
            itemPol.setBftamt(new BigDecimal("0.00"));
            itemPol.setBfttot(new BigDecimal("0.00"));
            itemPol.setCcag1fc(claimPolicyItem.getAgent().getCode());
            itemPol.setEaag1nm(claimPolicyItem.getAgent().getName());
            itemPol.setCcag2fc("");
            itemPol.setEaag2nm("");
            itemPol.setDclobj("");
            itemPol.setDclp2s(BigDecimal.ZERO);
            itemPol.setRecusr(Config.getOWNER_LOGIN());
            itemPol.setRecdte(DateTools.getDateNowYmd());

            crsfPolList.add(itemPol);
        }

        //-------------------------------------------
        CrsfClm crsfClm = new CrsfClm();
        crsfClm.setClmno(cgData.getClaimPolicyNumber());
        crsfClm.setCrfid("C");
        crsfClm.setClmon("");
        crsfClm.setStat(cgData.getClaimStatus());
        crsfClm.setStatdte(DateTools.getDateNowYmd());
        crsfClm.setChck("N");
        crsfClm.setFaok(claimPropertyObject.getClaimFAOKStatus());
        crsfClm.setPaok(claimPropertyObject.getClaimPAOKStatus());
        crsfClm.setCateg(cgData.getClaimCategory());
        crsfClm.setDeath(cgData.getDeathStatus());
        crsfClm.setCause(CAUSE);
        crsfClm.setEvent(EVENT);
        crsfClm.setIname(сlaimPolicyMain.getInsured().getNameIni());
        crsfClm.setIbthd(Convert.dateToStringWithFormat(сlaimPolicyMain.getInsured().getDob(), Convert.DATE_FORMAT_BASE));
        crsfClm.setIidno(сlaimPolicyMain.getInsured().getPassport());
        crsfClm.setIadr1(сlaimPolicyMain.getInsured().getFullAddress());
        crsfClm.setIadr2(сlaimPolicyMain.getInsured().getRegion());
        crsfClm.setIzip(сlaimPolicyMain.getInsured().getZip());
        crsfClm.setIcity(сlaimPolicyMain.getInsured().getCity());
        crsfClm.setIidno(сlaimPolicyMain.getInsured().getPassport());
        crsfClm.setEvtdt(Convert.dateToStringWithFormat(cgData.getOccuredDate(), Convert.DATE_FORMAT_BASE));
        crsfClm.setNtfdt(Convert.dateToStringWithFormat(cgData.getDocumentDate(), Convert.DATE_FORMAT_BASE));
        crsfClm.setEntdt(DateTools.getDateNowYmd());
        crsfClm.setCovamt(coverage.setScale(2, RoundingMode.HALF_UP));
        crsfClm.setResamt(totalPaid);
        crsfClm.setBftamt(new BigDecimal("0.00"));
        crsfClm.setBfttot(new BigDecimal("0.00"));
        crsfClm.setDclobj("");
        crsfClm.setDclp2s(new BigDecimal("0.00"));
        crsfClm.setHcllsqn(claimPropertyObject.getClaimHistoryHCLLSQN());
        crsfClm.setSetlsqn(claimPropertyObject.getClaimHistorySETLSQN());
        crsfClm.setFlwlsqn(claimPropertyObject.getClaimHistoryFLWLSQN());
        crsfClm.setFmelsqn(claimPropertyObject.getClaimHistoryFMELSQN());
        crsfClm.setFlelsqn(claimPropertyObject.getClaimHistoryFLELSQN());
        crsfClm.setSetpaid("");
        crsfClm.setSetpdte("");
        crsfClm.setFmeamt(new BigDecimal("0.00"));
        crsfClm.setFleamt(new BigDecimal("0.00"));
        crsfClm.setRecusr(Config.getOWNER_LOGIN());
        crsfClm.setRecdte(DateTools.getDateNowYmd());
        //----

        CrsFclmHi crsFclmHi = new CrsFclmHi();
        crsFclmHi.setClmno(cgData.getClaimPolicyNumber());
        crsFclmHi.setSeqno(lastHistoryNumber);
        crsFclmHi.setRstc(claimPropertyObject.getClaimPreviousStatus());
        crsFclmHi.setRstcdt(claimPropertyObject.getClaimPreviousDate());
        crsFclmHi.setAction(cgData.getClaimStatus());
        crsFclmHi.setActdte(DateTools.getDateNowYmd());
        crsFclmHi.setActdsc(ClaimsUtils.getDescForStatus(claimPropertyObject.getClaimStatus()));
        crsFclmHi.setRecusr(Config.getOWNER_LOGIN());
        crsFclmHi.setRecdte(DateTools.getDateNowYmd());
        //----

        if (itemLast.getPlanCode() != null) {
            ClaimPlanCode itemEib = new ClaimPlanCode();

            itemEib.setCoverage(BigDecimal.ZERO);
            itemEib.setCoverageLong(new Long(0));
            itemEib.setDaysHospital(0);
            itemEib.setEibStatus("A");
            itemEib.setEffectiveFrom(itemLast.getEffectiveFrom());
            itemEib.setEffectiveTo(itemLast.getEffectiveTo());
            itemEib.setPlanCode(eibPlancode);
            itemEib.setRelation(itemLast.getRelation());
            itemEib.setPolicyNumber(itemLast.getPolicyNumber());
            itemEib.setPremium(BigDecimal.ZERO);
            itemEib.setEffectiveFrom(itemLast.getEffectiveFrom());
            itemEib.setEffectiveTo(itemLast.getEffectiveTo());
            itemEib.setRider("EIB");
            if (сlaimPolicyMain.getEib() != null && сlaimPolicyMain.getEib().doubleValue() != 0) {
                itemEib.setCoverage(сlaimPolicyMain.getEib());
            }
            toBase.add(itemEib);
        }

        //RSB
        for (ClaimPlanCode planCode : toBase) {

            if (planCode.getPlanCode().equals("P0") || planCode.getPlanCode().equals("P1")
                    || planCode.getPlanCode().equals("C0") || planCode.getPlanCode().equals("C1")) {
                continue;
            }
            boolean extraType = planCode.getPlanCode().equals("W0") || planCode.getPlanCode().equals("W1") || planCode.getPlanCode().equals("WP");
            boolean eibType = planCode.getPlanCode().trim().equals(eibPlancode.trim());
            boolean plainType = !(eibType || extraType);
            String riderPyup = "";

            if (plainType) {
                //log.info(planCode.getPlanCode() + " => " + planCode.getPhaseClaim() + " " + planCode.getRider() + " " + (planCode.getPyupDate() != null ? planCode.getPyupDate().toString() : " is null "));
                if (planCode.getPaidDateTo() != null) {
                    riderPyup = Convert.dateToStringWithFormat(planCode.getPyupDate(), Convert.DATE_FORMAT_BASE);
                } else {
                    //log.info("PyupDate is null. Plancode=" + planCode.getPlanCode());
                }
            }
            BigDecimal paidCoverageReserve = planCode.getPaidCoverage() == null ? BigDecimal.ZERO : planCode.getPaidCoverage().setScale(2, RoundingMode.HALF_UP);
            if (planCode.getPaidCoverage() == null) {
                planCode.setPaidCoverage(BigDecimal.ZERO);
            }
            CrsfRsb crsfRsb = new CrsfRsb();
            crsfRsb.setRsbclmno(cgData.getClaimPolicyNumber());
            crsfRsb.setRsbpolno(planCode.getPolicyNumber());
            crsfRsb.setRsbrtype(eibType ? "RU" : (extraType ? "CS" : "CB"));
            crsfRsb.setRsbridnt(extraType ? planCode.getPhaseClaim() + planCode.getRider() + planCode.getOccur() : planCode.getRider());
            crsfRsb.setRsbrdupl("");
            crsfRsb.setRsbstat(paidCoverageReserve.compareTo(BigDecimal.ZERO) > 0 ? claimPropertyObject.getClaimStatus() : claimPropertyObject.getClaimPreviousStatus());
            crsfRsb.setRsbstatdte(DateTools.getDateNowYmd());
            crsfRsb.setRsbfaok(claimPropertyObject.getClaimFAOKStatus());
            crsfRsb.setRsbexgrac("N");
            crsfRsb.setRsbcbst(eibType ? "" : (extraType ? "1" : planCode.getStatus()));
            crsfRsb.setRsbplob(eibType || extraType ? "" : planCode.getLob() != null ? planCode.getLob() : "");
            crsfRsb.setRsbplcd(eibType || extraType ? planCode.getPlanCode() : " " + planCode.getPlanCode().trim());
            crsfRsb.setRsbplwc(eibType || extraType ? "" : "B");
            crsfRsb.setRsbhscp("");
            crsfRsb.setRsbhscfr(Convert.dateToStringWithFormat(planCode.getPaidDateFrom(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbhscto(Convert.dateToStringWithFormat(planCode.getPaidDateTo(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbhscfr2(Convert.dateToStringWithFormat(planCode.getPaidDateFrom2(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbhscto2(Convert.dateToStringWithFormat(planCode.getPaidDateTo2(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbhscfr3(Convert.dateToStringWithFormat(planCode.getPaidDateFrom3(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbhscto3(Convert.dateToStringWithFormat(planCode.getPaidDateTo3(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbefedte(eibType || extraType ? "" : Convert.dateToStringWithFormat(planCode.getEffectiveFrom(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbmatdte(eibType || extraType ? "" : Convert.dateToStringWithFormat(planCode.getEffectiveTo(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbpyudte(eibType ? "" : (extraType ? riderPyup : planCode.getPyupDate() != null ? Convert.dateToStringWithFormat(planCode.getPyupDate(), Convert.DATE_FORMAT_BASE) : ""));
            crsfRsb.setRsbrel(eibType || extraType ? "" : planCode.getRelation());
            crsfRsb.setRsbsex(eibType || extraType ? "" : planCode.getSex() != null ? planCode.getSex() : "");
            crsfRsb.setRsbinsname(eibType || extraType ? "" : ClaimsUtils.getPerson(planCode, сlaimPolicyMain).getNameIni());
            crsfRsb.setRsbinsbthd(eibType || extraType ? "" : Convert.dateToStringWithFormat(ClaimsUtils.getPerson(planCode, сlaimPolicyMain).getDob(), Convert.DATE_FORMAT_BASE));
            crsfRsb.setRsbissage(eibType ? 0 : planCode.getAge());
            crsfRsb.setRsbannprm(eibType ? BigDecimal.ZERO : ClaimsUtils.getChangeModalPrem(planCode.getPremium(), сlaimPolicyMain.getModality().toUpperCase(), "A"));
            crsfRsb.setRsbmodprm(eibType ? BigDecimal.ZERO : planCode.getPremium());
            crsfRsb.setRsbhscprm(new BigDecimal("0.00"));
            crsfRsb.setRsbcovamt(planCode.getCoverage().setScale(2, RoundingMode.HALF_UP));
            crsfRsb.setRsbrestyp("");
            crsfRsb.setRsbrespct(planCode.getProcentPayment() == null ? new BigDecimal("0.00") : planCode.getProcentPayment());
            crsfRsb.setRsbresamt(planCode.getPaidCoverage() == null ? BigDecimal.ZERO : planCode.getPaidCoverage().setScale(2, RoundingMode.HALF_UP));//res
            crsfRsb.setRsbresnew(new BigDecimal("0.00"));
            crsfRsb.setRsbbftpct(new BigDecimal("0.00"));
            crsfRsb.setRsbbftamt(new BigDecimal("0.00"));//paidcover
            crsfRsb.setRsbbfttot(new BigDecimal("0.00")); //paidcover??
            crsfRsb.setRsbpsettle("C");
            crsfRsb.setRsbdclobj("");
            crsfRsb.setRsbdclp2s(new BigDecimal("0.00"));
            crsfRsb.setRsbhislsqn(claimPropertyObject.getClaimHistoryHCLLSQN());
            crsfRsb.setRsbrecusr(Config.getOWNER_LOGIN());
            crsfRsb.setRsbrecdte(DateTools.getDateNowYmd());
            crsfRsbList.add(crsfRsb);

            if (planCode.getPaidCoverage().compareTo(BigDecimal.ONE) > 0 && cgData.getClaimPropertyObject().getClaimStatus().equals("U") == false) {
                CrsfRsbHi crsfRsbHi = new CrsfRsbHi();
                crsfRsbHi.setHrsbclmno(cgData.getClaimPolicyNumber());
                crsfRsbHi.setHrsbpolno(сlaimPolicyMain.getPolicyNumberString());
                crsfRsbHi.setHrsbsrctp(eibType ? "RU" : (extraType ? "CS" : "CB"));
                crsfRsbHi.setHrsbsrcid(extraType ? planCode.getPhaseClaim() + planCode.getRider() + planCode.getOccur() : planCode.getRider());
                crsfRsbHi.setHrsbrdupl("");
                crsfRsbHi.setHrsbseqno(lastHistoryNumber);
                crsfRsbHi.setHrsbsrplan(eibType || extraType ? planCode.getPlanCode() : " " + planCode.getPlanCode().trim());
                crsfRsbHi.setHrsbaction("OR");//CX - decline
                crsfRsbHi.setHrsbactdte(DateTools.getDateNowYmd());
                crsfRsbHi.setHrsbactdsc("Open reserve");
                crsfRsbHi.setHrsbtrstat("W");
                crsfRsbHi.setHrsbtrstdt(DateTools.getDateNowYmd());
                crsfRsbHi.setHrsbtrcode("+R");
                crsfRsbHi.setHrsbtrdate(DateTools.getDateNowYmd());
                crsfRsbHi.setHrsbtracn1("");
                crsfRsbHi.setHrsbtracn2("");
                crsfRsbHi.setHrsbtramnt(planCode.getPaidCoverage() == null ? BigDecimal.ZERO : planCode.getPaidCoverage().setScale(2, RoundingMode.HALF_UP));
                crsfRsbHi.setHrsbrecusr(Config.getOWNER_LOGIN());
                crsfRsbHi.setHrsbrecdte(DateTools.getDateNowYmd());
                crsfRsbHiList.add(crsfRsbHi);
                //---                  
            }

        }

//----
        ClmCrmNigo clmCrmNigo = new ClmCrmNigo();
        clmCrmNigo.setClaimNo(cgData.getClaimPolicyNumber());
        clmCrmNigo.setNigo(cgData.getNigo());
        //----
        ClmAgt clmAgt = new ClmAgt();
        clmAgt.setPolnum(" " + (cgData.getMainPolicyNumber().trim()));
        clmAgt.setClmnum(" " + (cgData.getClaimPolicyNumber().trim()));
        clmAgt.setStatus("Y");
        clmAgt.setAgtname1(cgData.getConsultantName());
        clmAgt.setAgtphone1(cgData.getConsultantPhone());
        clmAgt.setAgtname2("");
        clmAgt.setAgtphone2("");
        clmAgt.setAgtemail1("");
        clmAgt.setAgtemail2("");
        //----        

        //----transaction
        EntityManager em = DbConfigCrl.getEntityManager(); //R2D2
        EntityTransaction newTr = em.getTransaction();

        newTr.begin();
        try {
            
            for (int i = 0; i < crsfPolList.size(); i++) {
                log.info("itemPol - begin ");
                CrsfPol itemPolObj = crsfPolList.get(i);
                itemPolObj.setCrsfClm(crsfClm);
                em.persist(itemPolObj);
            }

            em.persist(crsfClm);
            if (cgData.getClaimPropertyObject().getClaimStatus().equals("U") == false) {
                em.persist(crsFclmHi);
            }

            for (int i = 0; i < crsfRsbList.size(); i++) {
                CrsfRsb item = crsfRsbList.get(i);
                em.persist(item);
            }
            for (int i = 0; i < crsfRsbHiList.size(); i++) {
                CrsfRsbHi item = crsfRsbHiList.get(i);
                em.persist(item);
            }

            em.persist(clmCrmNigo);
            em.persist(clmAgt);

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
