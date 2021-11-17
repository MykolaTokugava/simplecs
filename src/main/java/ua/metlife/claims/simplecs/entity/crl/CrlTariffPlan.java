/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crl;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NPopov
 */
@Entity
@Data
@Table(name = "CRL_TARIFF_PLAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlTariffPlan.findAll", query = "SELECT c FROM CrlTariffPlan c")})
public class CrlTariffPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TP_ID")
    private Integer tpId;
    @Basic(optional = false)
    @Column(name = "TP_NAME")
    private String tpName;
    @Column(name = "TP_CODE")
    private String tpCode;
    @Basic(optional = false)
    @Column(name = "TP_VALID_FROM")
    @Temporal(TemporalType.DATE)
    private Date tpValidFrom;
    @Basic(optional = false)
    @Column(name = "TP_VALID_TO")
    @Temporal(TemporalType.DATE)
    private Date tpValidTo;
    @Basic(optional = false)
    @Column(name = "TP_IS_ACTIVE")
    private short tpIsActive;
    @Column(name = "TP_REPAY_PERIOD")
    private Integer tpRepayPeriod;
    @Column(name = "TP_DESC")
    private String tpDesc;
    @Column(name = "OLD_ID")
    private Integer oldId;
    @Column(name = "TP_ALICO_PERCENT")
    private BigDecimal tpAlicoPercent;
    @Column(name = "TP_BANK_PERCENT")
    private BigDecimal tpBankPercent;
    @Column(name = "TP_BANK_COEFFICIENT")
    private BigDecimal tpBankCoefficient;
    @Column(name = "TP_USE_DEDUCT_AMOUNT")
    private Integer tpUseDeductAmount;
    @Column(name = "TP_DEDUCTION_AMOUNT")
    private BigDecimal tpDeductionAmount;
    @Column(name = "TP_ALICO_RENEW_PERCENT")
    private BigDecimal tpAlicoRenewPercent;
    @Column(name = "TP_BANK_RENEW_PERCENT")
    private BigDecimal tpBankRenewPercent;
    @Column(name = "TP_MATURITY_DATE_AGE")
    private Integer tpMaturityDateAge;
    @Column(name = "TP_DURATION_RANGE")
    private String tpDurationRange;
    @Column(name = "TP_MINAGE")
    private Integer tpMinage;
    @Column(name = "TP_MAXAGE")
    private Integer tpMaxage;
    @Column(name = "FIXED_AMOUNT")
    private Integer fixedAmount;
    @Column(name = "FIXED_PREMIUM")
    private Integer fixedPremium;
//    @Column(name = "BP_ID")
//    private Integer bp_Id;


//     @OneToMany(mappedBy = "currentTpId")
//     private List<CrlGeneral1c> crlGeneral1cList;

     @JoinColumn(name = "BP_ID", referencedColumnName = "BP_ID", insertable = false, updatable = false, nullable = true)
     @ManyToOne(optional = false)
     private CrlBankProduct bpId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tpId")
    private List<CrlTpCoef> crlTpCoefList;


    /*
     @OneToMany(mappedBy = "tpId")
     private List<CrlGeneralBank> crlGeneralBankList;
    @OneToMany(mappedBy = "tpId")
     private List<CrlPayment> crlPaymentList;
     */

}
