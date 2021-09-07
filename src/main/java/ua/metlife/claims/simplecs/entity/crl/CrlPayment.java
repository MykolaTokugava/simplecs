/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author NPopov
 */
@Entity
@Table(name = "CRL_PAYMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlPayment.findAll", query = "SELECT c FROM CrlPayment c")})
public class CrlPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PAID_FROM")
    private String paidFrom;
    @Column(name = "PAID_TO")
    private String paidTo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "PREMIUM_CALCULATED")
    private BigDecimal premiumCalculated;
    @Column(name = "PREMIUM_PAID")
    private BigDecimal premiumPaid;
    @Column(name = "PREMIUM_DIFF")
    private BigDecimal premiumDiff;
    @Column(name = "BANK_SHARE_CALCULATED")
    private BigDecimal bankShareCalculated;
    @Column(name = "BANK_SHARE_PAID")
    private BigDecimal bankSharePaid;
    @Column(name = "BANK_SHARE_DIFF")
    private BigDecimal bankShareDiff;
    @Column(name = "OUR_SHARE_CALCULATED")
    private BigDecimal ourShareCalculated;
    @Column(name = "OUR_SHARE_PAID")
    private BigDecimal ourSharePaid;
    @Column(name = "OUR_SHARE_DIFF")
    private BigDecimal ourShareDiff;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ERROR_FLAG")
    private String errorFlag;
    @Column(name = "DATE_1C")
    private String date1c;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "RAW_DATA")
    private String rawData;
    @Column(name = "IMPORT_ID")
    private Integer importId;
    @Column(name = "OLD_1C_ID")
    private Integer old1cId;
    @Basic(optional = false)
    @Column(name = "PAYMENT_MONTH")
    private String paymentMonth;
    @OneToMany(mappedBy = "refPaymentId")
    private List<CrlPayment> crlPaymentList;
    @JoinColumn(name = "REF_PAYMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private CrlPayment refPaymentId;
    @JoinColumn(name = "TP_ID", referencedColumnName = "TP_ID")
    @ManyToOne
    private CrlTariffPlan tpId;

    @JoinColumn(name = "GENERAL_ID", referencedColumnName = "ID", insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private CrlGeneral1c generalId;

    @JoinColumn(name = "ID", referencedColumnName = "RELATED_1C_GEN_ID", insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private CrlGeneralBank crlGeneralBank;


    public CrlPayment() {
    }

    public CrlPayment(Integer id) {
        this.id = id;
    }

    public CrlPayment(Integer id, String status, String paymentMonth) {
        this.id = id;
        this.status = status;
        this.paymentMonth = paymentMonth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaidFrom() {
        return paidFrom;
    }

    public void setPaidFrom(String paidFrom) {
        this.paidFrom = paidFrom;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPremiumCalculated() {
        return premiumCalculated;
    }

    public void setPremiumCalculated(BigDecimal premiumCalculated) {
        this.premiumCalculated = premiumCalculated;
    }

    public BigDecimal getPremiumPaid() {
        return premiumPaid;
    }

    public void setPremiumPaid(BigDecimal premiumPaid) {
        this.premiumPaid = premiumPaid;
    }

    public BigDecimal getPremiumDiff() {
        return premiumDiff;
    }

    public void setPremiumDiff(BigDecimal premiumDiff) {
        this.premiumDiff = premiumDiff;
    }

    public BigDecimal getBankShareCalculated() {
        return bankShareCalculated;
    }

    public void setBankShareCalculated(BigDecimal bankShareCalculated) {
        this.bankShareCalculated = bankShareCalculated;
    }

    public BigDecimal getBankSharePaid() {
        return bankSharePaid;
    }

    public void setBankSharePaid(BigDecimal bankSharePaid) {
        this.bankSharePaid = bankSharePaid;
    }

    public BigDecimal getBankShareDiff() {
        return bankShareDiff;
    }

    public void setBankShareDiff(BigDecimal bankShareDiff) {
        this.bankShareDiff = bankShareDiff;
    }

    public BigDecimal getOurShareCalculated() {
        return ourShareCalculated;
    }

    public void setOurShareCalculated(BigDecimal ourShareCalculated) {
        this.ourShareCalculated = ourShareCalculated;
    }

    public BigDecimal getOurSharePaid() {
        return ourSharePaid;
    }

    public void setOurSharePaid(BigDecimal ourSharePaid) {
        this.ourSharePaid = ourSharePaid;
    }

    public BigDecimal getOurShareDiff() {
        return ourShareDiff;
    }

    public void setOurShareDiff(BigDecimal ourShareDiff) {
        this.ourShareDiff = ourShareDiff;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(String errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String getDate1c() {
        return date1c;
    }

    public void setDate1c(String date1c) {
        this.date1c = date1c;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    public Integer getOld1cId() {
        return old1cId;
    }

    public void setOld1cId(Integer old1cId) {
        this.old1cId = old1cId;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    @XmlTransient
    public List<CrlPayment> getCrlPaymentList() {
        return crlPaymentList;
    }

    public void setCrlPaymentList(List<CrlPayment> crlPaymentList) {
        this.crlPaymentList = crlPaymentList;
    }

    public CrlPayment getRefPaymentId() {
        return refPaymentId;
    }

    public void setRefPaymentId(CrlPayment refPaymentId) {
        this.refPaymentId = refPaymentId;
    }

    public CrlTariffPlan getTpId() {
        return tpId;
    }

    public void setTpId(CrlTariffPlan tpId) {
        this.tpId = tpId;
    }

    public CrlGeneral1c getGeneralId() {
        if (generalId==null) return new CrlGeneral1c();
        return generalId;
    }

    public void setGeneralId(CrlGeneral1c generalId) {
        this.generalId = generalId;
    }

    public CrlGeneralBank getCrlGeneralBank() {
        return crlGeneralBank;
    }

    public void setCrlGeneralBank(CrlGeneralBank crlGeneralBank) {
        this.crlGeneralBank = crlGeneralBank;
    }
}
