/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimsEntity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author NPopov
 */
@Slf4j
@Data
public class ClaimPlanCode implements Serializable {

    private String policyNumber;
    private String planCode;
    private String programm;
    private String description;
    private BigDecimal coverage = BigDecimal.ZERO;
    private Long coverageLong;
    private BigDecimal paidCoverage = BigDecimal.ZERO;
    private BigDecimal premium = BigDecimal.ZERO;
    private BigDecimal procentPayment = BigDecimal.ZERO;
    private Integer daysHospital = 0;
    private Date paidDateFrom;
    private Date paidDateTo;
    private Date paidDateFrom2;
    private Date paidDateTo2;
    private Date paidDateFrom3;
    private Date paidDateTo3;
    private String relation;
    private Date effectiveFrom;
    private Date effectiveTo;
    private String status;
    private String rider;
    private String gracePeriodStatus;
    private String watingPeriodStatus;
    private String eibStatus;
    private String taxStatus;
    private String phaseClaim;
    private Integer occur = 0;
    private Date pyupDate;
    private String lob;
    private Integer age;
    private String sex;
    private Boolean planCodeStatus;

    public String toString() {
        return policyNumber.trim() + "/" + planCode.trim();
    }

    public Long getCoverageLong() {
        return coverage.longValue();
    }

    public Boolean isDiclineStatus() {
        if (planCodeStatus == null) {
            return false;
        } else {
            return planCodeStatus;
        }
    }

}
