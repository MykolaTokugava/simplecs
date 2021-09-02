package ua.metlife.claims.simplecs.ClaimAction;

import ua.metlife.claims.simplecs.ClaimsEntity.ClaimIntegrator;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimPlanCode;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimPropertyObject;
import ua.metlife.claims.simplecs.processing.DateTools;

import java.util.ArrayList;
import java.util.List;

public class AddNewClaim {

    private List<ClaimPlanCode> resultList = new ArrayList();
    private java.util.List<ClaimPlanCode> listPlanCodes;
    private ClaimIntegrator claimIntegrator;
    private ClaimPlanCode selectedObject;
    private int _COL_DAYS = 14;
    private int _COL_PAID_COVERAGE = 6;
    private int _COL_PRSNT = 15;
    private int _COL_EFFECTIVE_DATE = 16;
    private int _COL_WAITING = 20;


    public List<ClaimPlanCode> getListPlanCodes() {
        if (listPlanCodes == null) {
            listPlanCodes = new ArrayList<>();
        }
        return listPlanCodes;
    }

    public void setListPlanCodes(List<ClaimPlanCode> list) {
        this.listPlanCodes = list;
    }

    public ClaimIntegrator getClaimIntegrator() {
        if (claimIntegrator == null) {
            claimIntegrator = new ClaimIntegrator();
        }
        return claimIntegrator;
    }

    public void addNewClaim() {



        String claimCategory = "A";
        if (claimCategory.equals("D")) {
            claimCategory = "D";
        } else if (claimCategory.equals("A")) {
            claimCategory = "A";
        } else if (claimCategory.equals("OD")) {
            claimCategory = "OD";
        } else if (claimCategory.equals("AD")) {
            claimCategory = "AD";
        }

        //set data for opening status
        ClaimPropertyObject claimPropertyObject = new ClaimPropertyObject();
        claimPropertyObject.setClaimPreviousDate(DateTools.getDateNowYmd());
        String claimActionStatus = "UnOpen";
        if ("UnOpen".equals(claimActionStatus)) {
            claimIntegrator.getGeneralClaimData().setClaimStatus("U");
        } else if ("Open".equals(claimActionStatus)) {
            claimIntegrator.getGeneralClaimData().setClaimStatus("O1");
        }
        claimIntegrator.getGeneralClaimData().setClaimPropertyObject(claimPropertyObject);
        claimIntegrator.getGeneralClaimData().setClaimCategory(claimCategory);
        claimIntegrator.getGeneralClaimData().setNigo(1);//1 : 0
        claimIntegrator.getGeneralClaimData().setDeathStatus("N");//"Y" : "N"
//        claimIntegrator.getGeneralClaimData().setConsultantName(jTextFieldConsultantName.getText().trim());
//        claimIntegrator.getGeneralClaimData().setConsultantPhone(jTextFieldConsultantPhone.getText().trim());
//        claimIntegrator.getGeneralClaimData().setDocumentDate(jDateChooserDocument.getDate());
//        claimIntegrator.getGeneralClaimData().setOccuredDate(jDateChooserOccured.getDate());
//        claimIntegrator.getGeneralClaimData().setMainPolicyNumber(claimIntegrator.getGeneralClaimData().getMainPolicyNumber());
//        claimIntegrator.getGeneralClaimData().setClaimCause(jComboBoxClaimCouse.getSelectedItem() == null ? " " : ((CrsFccc) jComboBoxClaimCouse.getSelectedItem()).getCode());
//        claimIntegrator.getGeneralClaimData().setClaimReason((CrsFccc) jComboBoxReasonGroup.getSelectedItem() == null ? " " : ((CrsFccc) jComboBoxReasonGroup.getSelectedItem()).getCode());

//        ClaimDoToCrs claimDo = new ClaimDoToCrs();
        ClaimOpen claimOpen = new ClaimOpen(claimIntegrator);

    }

}
