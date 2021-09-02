/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimsEntity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author NPopovgetGeneralData
 */
@Data
public class ClaimCollector implements Serializable {

    private static final long serialVersionUID = 1L;


    private ClaimPolicy claimPolicy;

    private CreditLifeObject creditLife;
    //private GroupLifeObject groupLife;
    //private OlasPolicyStructure olasStr;

    private Boolean toUseInsuredOrPayer;



    public ClaimPolicy getClaimPolicy() {
        if (claimPolicy == null) {
            claimPolicy = new ClaimPolicy();
        }
        return claimPolicy;
    }

    public String toString() {
        return getClaimPolicy().getPolicyNumberString();
    }

}
