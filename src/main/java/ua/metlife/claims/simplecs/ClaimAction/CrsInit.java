/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.ClaimAction;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimIntegrator;
import ua.metlife.claims.simplecs.ClaimsEntity.ClaimPlanCode;
import ua.metlife.claims.simplecs.ClaimsEntity.SystemsEnum;
import ua.metlife.claims.simplecs.entity.crs.*;
import ua.metlife.claims.simplecs.processing.Config;
import ua.metlife.claims.simplecs.processing.Convert;

import java.util.List;

/**
 *
 * @author NPopov
 */
@Slf4j
@Data
public class CrsInit {


	public CrsInit(ClaimIntegrator claimIntegrator) {

		List<CrsfPol> polList = claimIntegrator.getEntityManager().createNamedQuery("CrsfPol.findById").setParameter("clmNumber", claimIntegrator.getExternalInitData().getClaimNumber()).getResultList();
		CrsfPol crsfPol = polList.get(0);
		claimIntegrator.setCrsfPol(crsfPol);
		if (polList.get(0) != null) {
			List<CrsfRsb> ridersList = polList.get(0).getRidersRsbList();
			List<CrsFclmHi> historyList = polList.get(0).getClaimHistory();
			List<CrsfRsbHi> historyRsbList = polList.get(0).getClaimRsbHistory();

			log.info("" + polList.get(0).getCrsfClm().getIname());
			log.info("Riders = " + ridersList.size());
			log.info("History = " + historyList.size());
			log.info("Rsb History = " + historyRsbList.size());
			initPlanCodesOlas(ridersList, claimIntegrator);
			generalInit(claimIntegrator, crsfPol);
		}

	}

	static void initPlanCodesOlas(List<CrsfRsb> ridersList, ClaimIntegrator claimIntegrator) {

		if (ridersList != null) {

			for (CrsfRsb rider : ridersList) {

				//log.info("rider = {}", rider.getRsbplcd());
				ClaimPlanCode item = new ClaimPlanCode();
				item.setPolicyNumber(rider.getRsbpolno());
				item.setRider(rider.getRsbridnt());
				item.setStatus(rider.getRsbstat());
				item.setEffectiveFrom(Convert.stringToDateFormat(Convert.DATE_FORMAT_BASE, rider.getRsbefedte()));
				item.setEffectiveTo(Convert.stringToDateFormat(Convert.DATE_FORMAT_BASE, rider.getRsbmatdte()));
				item.setRelation(rider.getRsbrel());
				item.setPlanCode(rider.getRsbplcd());
				item.setPhaseClaim("PhaseClaim");
				item.setOccur(666777);
				item.setPyupDate(Convert.stringToDateFormat(Convert.DATE_FORMAT_BASE, rider.getRsbpyudte()));
				item.setLob(rider.getRsbplob());
				item.setAge(rider.getRsbissage());
				item.setSex(rider.getRsbsex());
				item.setDaysHospital(null);
				item.setProcentPayment(rider.getRsbrespct());
				item.setPremium(rider.getRsbmodprm());
				item.setProgramm("Desc");
				item.setCoverage(rider.getRsbcovamt());
				item.setPaidCoverage(rider.getRsbresamt());
				item.setDescription("desc2?");
				item.setPaidDateFrom(Convert.stringToDateFormat(Convert.DATE_FORMAT_BASE, rider.getRsbhscfr()));
				item.setPaidDateTo(Convert.stringToDateFormat(Convert.DATE_FORMAT_BASE, rider.getRsbhscto()));

				claimIntegrator.getGeneralClaimData().setOpenReserves(claimIntegrator.getGeneralClaimData().getOpenReserves().add(rider.getRsbresamt()));

				claimIntegrator.getGeneralClaimData().getClaimsPlanCodes().add(item);
			}

		}

	}

	public void generalInit(ClaimIntegrator claimIntegrator, CrsfPol crsfPol) {

		List<ClmAgt> agtList = claimIntegrator.getEntityManager().createNamedQuery("ClmAgt.findByClmNum").setParameter("clmNumber", claimIntegrator.getExternalInitData().getClaimNumber()).getResultList();
		ClmAgt agent = null;
		if (agtList.size() > 0) {
			agent = agtList.get(0);
		}
		List<ClmCrmNigo> nigoList = claimIntegrator.getEntityManager().createNamedQuery("ClmCrmNigo.findById").setParameter("clmNumber", claimIntegrator.getExternalInitData().getClaimNumber()).getResultList();

		ClmCrmNigo nigo = null;
		if (nigoList.size() > 0) {
			nigo = nigoList.get(0);
		}

		claimIntegrator.getGeneralClaimData().setClaimOwner(Config.getOWNER_LOGIN());
		claimIntegrator.getGeneralClaimData().setInsuredName(crsfPol.getIname());
		claimIntegrator.getGeneralClaimData().setPayerName(crsfPol.getOname());
		claimIntegrator.getGeneralClaimData().setMainPolicyNumber(crsfPol.getPolno());
		claimIntegrator.getGeneralClaimData().setSystem(SystemsEnum.OLAS.toString());
		claimIntegrator.getGeneralClaimData().setClaimPolicyNumber(crsfPol.getClmno());
		claimIntegrator.getGeneralClaimData().setClaimStatus(crsfPol.getStat());
		claimIntegrator.getGeneralClaimData().setClaimCategory(crsfPol.getCrsfClm().getCateg().trim());
//        String CAUSE = "";
//        String EVENT = "";
//        if (claimIntegrator.getGeneralClaimData().getClaimCategory().equals("D") || claimIntegrator.getGeneralClaimData().getClaimCategory().equals("OD")) {
//            CAUSE = claimIntegrator.getGeneralClaimData().getClaimReason() == null ? "" : claimIntegrator.getGeneralClaimData().getClaimReason();
//            EVENT = "";
//        } else {
//            CAUSE = claimIntegrator.getGeneralClaimData().getClaimCause() == null ? "" : claimIntegrator.getGeneralClaimData().getClaimCause();
//            EVENT = claimIntegrator.getGeneralClaimData().getClaimReason() == null ? "" : claimIntegrator.getGeneralClaimData().getClaimReason();
//        }

		claimIntegrator.getGeneralClaimData().setClaimReason(crsfPol.getCrsfClm().getEvent().trim());
		claimIntegrator.getGeneralClaimData().setClaimCause(crsfPol.getCrsfClm().getCause().trim());

		claimIntegrator.getGeneralClaimData().setOccuredDate(Convert.stringToDateFormat(Convert.DATE_FORMAT_BASE, crsfPol.getCrsfClm().getEvtdt().trim()));
		claimIntegrator.getGeneralClaimData().setDocumentDate(Convert.stringToDateFormat(Convert.DATE_FORMAT_BASE, crsfPol.getCrsfClm().getNtfdt().trim()));
		claimIntegrator.getGeneralClaimData().setConsultantName(agent == null ? "" : agent.getAgtname1().trim());
		claimIntegrator.getGeneralClaimData().setConsultantPhone(agent == null ? "" : agent.getAgtphone1().trim());
		claimIntegrator.getGeneralClaimData().setNigo(nigo == null ? 0 : nigo.getNigo());
		claimIntegrator.getGeneralClaimData().setDeclineCode(crsfPol.getCrsfClm().getDclobj().trim() + "_" + crsfPol.getCrsfClm().getDclp2s().toString());
	}

}
