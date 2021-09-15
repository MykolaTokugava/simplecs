/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crl;

import lombok.Data;

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
@Data
public class CrlPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "GENERAL_ID")
    private Integer general_Id;
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
//    @OneToMany(mappedBy = "refPaymentId")
//    private List<CrlPayment> crlPaymentList;
//    @JoinColumn(name = "REF_PAYMENT_ID", referencedColumnName = "ID")
//    @ManyToOne
//    private CrlPayment refPaymentId;
//    @JoinColumn(name = "TP_ID", referencedColumnName = "TP_ID")
//    @ManyToOne
//    private CrlTariffPlan tpId;
//
//    @JoinColumn(name = "GENERAL_ID", referencedColumnName = "ID", insertable = false, updatable = false, nullable = true)
//    @ManyToOne(optional = false)
//    private CrlGeneral1c generalId;
//
//    @JoinColumn(name = "ID", referencedColumnName = "RELATED_1C_GEN_ID", insertable = false, updatable = false, nullable = true)
//    @ManyToOne(optional = false)
//    private CrlGeneralBank crlGeneralBank;

}
