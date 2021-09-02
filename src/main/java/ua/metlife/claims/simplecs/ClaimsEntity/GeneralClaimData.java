/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimsEntity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NPopov
 */
@Data
public class GeneralClaimData {

    private String claimOwner;
    private String policyNumber;
    private String insuredName;    
    private String payerName;    
    private String claimStatus;    
    private BigDecimal openReserves= BigDecimal.ZERO;    
    
    private Integer nigo; 
    private String deathStatus;
    private String claimType;
    private String claimCategory;
    private String claimReason;
    private String claimCause;

    private String mainPolicyNumber;
    private String claimPolicyNumber;
    private String consultantName;
    private String consultantPhone;
    private String system;
    private Date documentDate;
    private Date occuredDate;
    
    private String declineCode;

    private List<ClaimPlanCode> claimsPlanCodes;//for panel filtered
    
    public List<ClaimPlanCode> getClaimsPlanCodes() {
        if (claimsPlanCodes == null) {
            claimsPlanCodes = new ArrayList<>();
        }
        return claimsPlanCodes;
    }    
    
    private ClaimPropertyObject claimPropertyObject;

}
