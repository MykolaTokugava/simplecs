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
public class ClaimPolicy {

    private String policyNumberString;
    private Integer policyNumberInt;
    private String status;
    private String claimPolicyNumber;
    private Date claimDate;
    private Date addDate;

    private Date issueDate;
    private Date maturityDate;
    private Date paidDate;
    private String modality;
    private BigDecimal premiumModality;
    private BigDecimal premiumYear;

    private ClientData insured;
    private ClientData payer;
    private PolicyAgent agent;
    private List<ClaimPlanCode> planCodes;
    private BigDecimal eib;

    private BigDecimal paidCoverageTotal;

    public ClientData getInsured() {
        if (insured == null) {
            insured = new ClientData();
        }
        return insured;
    }

    public ClientData getPayer() {
        if (payer == null) {
            payer = new ClientData();
        }

        return payer;
    }

    public List<ClaimPlanCode> getPlanCodes() {
        if (planCodes == null) {
            planCodes = new ArrayList<>();
        }
        return planCodes;
    }

    public PolicyAgent getAgent() {
        if (agent == null) {
            agent = new PolicyAgent();
        }

        return agent;
    }

}
