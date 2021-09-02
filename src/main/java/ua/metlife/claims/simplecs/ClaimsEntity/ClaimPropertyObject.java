/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimsEntity;

import lombok.Data;

/**
 *
 * @author NPopov
 */
@Data
public class ClaimPropertyObject {

    private String claimPreviousStatus;
    private String claimPreviousDate;
    private String claimStatus;
    private String claimFAOKStatus;
    private String claimPAOKStatus;
    private String claimRAOKStatus;
    private String claimHistoryHCLLSQN = "0000";
    private String claimHistorySETLSQN = "0000";
    private String claimHistoryFLWLSQN = "0000";
    private String claimHistoryFMELSQN = "0000";
    private String claimHistoryFLELSQN = "0000";

}
