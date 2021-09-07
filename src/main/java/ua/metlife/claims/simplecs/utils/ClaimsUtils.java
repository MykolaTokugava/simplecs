/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimPlanCode;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimPolicy;
import ua.metlife.claims.simplecs.ClaimsEntity.ClientData;
import ua.metlife.claims.simplecs.processing.DateTools;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author NPopov
 */
@Slf4j
@Data
public class ClaimsUtils {

    public static String getNewClaimNumberOlas(EntityManager entityManager) {
        String claimNumber = "";
        int curYear = Calendar.getInstance().get(Calendar.YEAR) - 2000;
        String sql = "select max(cast(substr(CLMNO,5,10) as int))+1 as LN "
                + "from  CRSFCLM  "
                + "where substr(CLMNO,2,2)= ?1 ";
        log.info("sql = " + sql);
        log.info("curYear = " + curYear);

        Query query = entityManager.createNativeQuery(sql); //R2D2

        claimNumber = " " + curYear + "." + getWithZerro("", ("" + (Integer) (query.setParameter(1, curYear).getResultList().get(0))));

        return claimNumber;

    }

    public static String getWithZerro(String prefix, String counter) {
log.info("counter = " + counter);
        if (counter.equals("null") || counter=="") {
            return prefix + "000001";
        } else if (counter.toString().length() == 1) {
            return prefix + "00000" + counter;
        } else if (counter.toString().length() == 2) {
            return prefix + "0000" + counter;
        } else if (counter.toString().length() == 3) {
            return prefix + "000" + counter;
        } else if (counter.toString().length() == 4) {
            return prefix + "00" + counter;
        } else if (counter.toString().length() == 5) {
            return prefix + "0" + counter;
        }
        return "";
    }

    public static BigDecimal calculateTotalCoverage(List<ClaimPlanCode> listCodes) {
        BigDecimal coverage = BigDecimal.ZERO;
        for (ClaimPlanCode pc : listCodes) {
            coverage = pc.getCoverage().add(coverage);
        }
        return coverage;
    }
    
    public static BigDecimal calculateTotalPaid(List<ClaimPlanCode> toBase) {
        BigDecimal res = BigDecimal.ZERO;
        for (int i=0; i<toBase.size(); i++) {
            ClaimPlanCode item = toBase.get(i);
            res = res.add(item.getPaidCoverage() == null ? BigDecimal.ZERO : item.getPaidCoverage());
        }
        return res;
    }    

    public static String getLastHistoryNumber(EntityManager entityManager, String claimNumber) {
        String historyNumber = "0001";
        String sql = "select max(cast(substr(SEQNO,3,4) as int))+1 as LN from  CRSFCLMHI  where CLMNO = ?1 ";

        Query query = entityManager.createNativeQuery(sql);

        Integer res = (Integer) (query.setParameter(1, claimNumber).getResultList().get(0));
        log.info("getLastHistoryNumber res = " + res);
        if (res != null) {
            if (res < 10) {
                historyNumber = "000" + (Integer) (query.setParameter(1, claimNumber).getResultList().get(0));
            } else if ((res >= 10)) {
                historyNumber = "00" + (Integer) (query.setParameter(1, claimNumber).getResultList().get(0));
            }
        }

        log.info("historyNumber = " + historyNumber);

        return historyNumber;

    }

    public static String getNextSettledNumber(EntityManager entityManager, String claimNumber) {
        String historyNumber = "0001";
        String sql = "select max(cast(substr(SETSETNO,3,4) as int))+1 as LN from CRSFSET where substr(SETCURRNO,1,2)=SUBSTR(CHAR(CURRENT DATE, ISO), 3, 2) AND SETCLMNO = ?1 ";

        Query query = entityManager.createNativeQuery(sql);

        Integer res = (Integer) (query.setParameter(1, claimNumber).getResultList().get(0));
        log.info("getLastSetteledNumber res = " + res);
        if (res != null) {
            if (res < 10) {
                historyNumber = "000" + (Integer) (query.setParameter(1, claimNumber).getResultList().get(0));
            } else if ((res >= 10)) {
                historyNumber = "00" + (Integer) (query.setParameter(1, claimNumber).getResultList().get(0));
            }
        }

        log.info("historyNumber = " + historyNumber);

        return historyNumber;

    }

    public static String getDescForStatus(String status) {
        String res = "";
        Map<String, String> infoStatus = new TreeMap();
        infoStatus.put("O1", "Open claim");
        infoStatus.put("DC", "Decline claim");
        infoStatus.put("CU", "Close claim");
        infoStatus.put("CS", "Close claim");
        infoStatus.put("OR", "Reopen claim");
        infoStatus.put("CX", "Decline claim");

        if (infoStatus.containsKey(status)) {
            return infoStatus.get(status);
        }

        return "--";
    }

    public static BigDecimal getChangeModalPrem(BigDecimal curPrem, String curModal, String setModal) {

        BigDecimal res = curPrem;

        if (curModal.equals(setModal)) {
            return res;
        }

        if (curModal.equals("A") && setModal.equals("S")) {
            res = getCalculateModalCorrect(curPrem, new BigDecimal("0.52"));
        } else if (curModal.equals("A") && setModal.equals("Q")) {
            res = getCalculateModalCorrect(curPrem, new BigDecimal("0.26"));
        } else if (curModal.equals("S") && setModal.equals("A")) {
            res = getCalculateModalCorrectDivide(curPrem, new BigDecimal("0.52"));
        } else if (curModal.equals("S") && setModal.equals("Q")) {
            res = getCalculateModalCorrectDivide(curPrem, new BigDecimal("2"));
        } else if (curModal.equals("Q") && setModal.equals("S")) {
            res = getCalculateModalCorrect(curPrem, new BigDecimal("2"));
        } else if (curModal.equals("Q") && setModal.equals("A")) {
            res = getCalculateModalCorrectDivide(curPrem, new BigDecimal("0.26"));
        }

        //log.info("curPrem = " + curPrem + " curModal = " + curModal + " setModal = " + setModal + " >> result = " + res);
        return res;

    }

    public static BigDecimal getCalculateModalCorrect(BigDecimal prem, BigDecimal rate) {

        BigDecimal result = BigDecimal.ZERO;
        result
                = prem.
                multiply(
                        rate
                )
                .setScale(0, RoundingMode.HALF_EVEN);

        return result;

    }

    public static BigDecimal getCalculateModalCorrectDivide(BigDecimal prem, BigDecimal rate) {

        BigDecimal result = BigDecimal.ZERO;
        result
                = prem.
                divide(rate, 0, RoundingMode.HALF_EVEN);

        return result;

    }

    public static ClientData getPerson(ClaimPlanCode item, ClaimPolicy сlaimPolicy) {
        return item.getRelation().equals("I") ? сlaimPolicy.getInsured() : сlaimPolicy.getPayer();
    }

    public static String getNextSETCURRNO(EntityManager entityManager) {

        String sql = "select max(cast(substr(SETCURRNO,3) as int)) as LN from CRSFSET  where substr(SETCURRNO,1,2)=SUBSTR(CHAR(CURRENT DATE, ISO), 3, 2)";
        Integer year = DateTools.getCurrentYear() - 2000;
        Query query = entityManager.createNativeQuery(sql);

        Integer res = (Integer) (query.getResultList().get(0));
        if (res == null) {
            return year + "0001";
        } 
        return year + "" + (res + 1);

    }

    
   


}
