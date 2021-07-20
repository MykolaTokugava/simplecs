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

/**
 *
 * @author NPopov
 */
@Entity
@Table(name = "CRL_GENERAL_1C")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlGeneral1c.findAll", query = "SELECT c FROM CrlGeneral1c c"),
    @NamedQuery(name = "CrlGeneral1c.findClaimsByInnOrName", query = "SELECT c  FROM CrlGeneral1c c where c.taxcode=:taxcode order by c.taxcode, c.customerFullName")        
}
)

//OR lower(c.customerFullName) like :searchKey 

@Data
public class CrlGeneral1c implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "POLICY_ID")
    private Integer policyId;    
    @Column(name = "ISSUE_DATE")
    private String issueDate;
    @Column(name = "MATURE_DATE")
    private String matureDate;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "CUSTOMER_FULL_NAME")
    private String customerFullName;
    @Column(name = "CUSTOMER_DATE_OF_BIRTH")
    private String customerDateOfBirth;
    @Column(name = "TAXCODE")
    private String taxcode;
    @Column(name = "CLAIM")
    private Integer claim;
    @Column(name = "CLAIM_DATE")
    private String claimDate;
    @Column(name = "FULL_REFUND")
    private Integer fullRefund;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "ORIGINAL_RAW_DATA")
    private String originalRawData;
    @Column(name = "ERROR_FLAG")
    private String errorFlag;
    @Column(name = "PREMATURE_CLOSURE")
    private Integer prematureClosure;

    @JoinColumn(name = "CURRENT_TP_ID", referencedColumnName = "TP_ID")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private CrlTariffPlan currentTpId;

    /*
     @JoinColumn(name = "POLICY_ID", referencedColumnName = "ID")
     @OneToOne(optional = false, fetch = FetchType.LAZY)
     private CrlPolicy policyId;

     @JoinColumn(name = "ID", referencedColumnName = "GENERAL_ID")
     @OneToOne(optional = false, fetch = FetchType.LAZY)
     private CrlPolicy CrlPayment;
     */
}
