/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimsEntity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ua.metlife.claims.simplecs.entity.crs.CrsfDcl;
import ua.metlife.claims.simplecs.entity.crs.CrsfPol;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NPopov
 */
@Slf4j
@Data
public class ClaimIntegrator {

    private List<ClaimCollector> claimCollectorList;
    private List<ClaimPlanCode> claimPlanCodesList;
    private GeneralClaimData generalClaimData;

    private ClaimActionEnum claimActionStatus;
    private ExternalInitData externalInitData;
    private List<String> polUniq;

    private CrsfPol crsfPol;
    CrsfDcl crsfDcl;
    
    public List<String> getPolUniq() {
        if (polUniq==null) {
            polUniq = new ArrayList();
        }
        return polUniq;
    }
    
    public ExternalInitData getExternalInitData() {
        if (externalInitData == null) {
            externalInitData = new ExternalInitData();
        }
        return externalInitData;
    }

    public ClaimActionEnum getClaimActionStatus() {
        return claimActionStatus == null ? ClaimActionEnum.DEFAULT : claimActionStatus;
    }

    public GeneralClaimData getGeneralClaimData() {
        if (generalClaimData == null) {
            generalClaimData = new GeneralClaimData();
        }
        return generalClaimData;
    }

    public void deleteClaim(ClaimCollector claimCollector) {
        getClaimCollectorList().remove(claimCollector);

        for (ClaimPlanCode item : claimCollector.getClaimPolicy().getPlanCodes()) {
            getClaimPlanCodesList().remove(item);
        }

    }

    public void deleteClaimAll() {
        getClaimCollectorList().clear();
        getClaimPlanCodesList().clear();
    }

    public List<ClaimCollector> getClaimCollectorList() {
        if (claimCollectorList == null) {
            claimCollectorList = new ArrayList<>();
        }
        return claimCollectorList;
    }

    public List<ClaimPlanCode> getClaimPlanCodesList() {
        if (claimPlanCodesList == null) {
            claimPlanCodesList = new ArrayList<>();
        }
        return claimPlanCodesList;
    }

    public ClaimCollector getMainPolicy() {

        if (getClaimCollectorList().iterator().hasNext() != false) {
            return getClaimCollectorList().get(0);
        } else {
            return null;

        }
    }

}
