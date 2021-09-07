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
@Table(name = "CRL_GENERAL_BANK")
@XmlRootElement
@Data
@NamedQueries({
    @NamedQuery(name = "CrlGeneralBank.findAll", query = "SELECT c FROM CrlGeneralBank c"),
    @NamedQuery(name = "CrlGeneralBank.findClaimsByDateClaim", query = "SELECT b  FROM CrlGeneralBank b , CrlGeneral1c c where c.id = b.related1cGenId AND c.claim=1 AND c.claimDate>=:paidDate order by b.taxcode, b.customerPassport"),
    @NamedQuery(name = "CrlGeneralBank.findClaimsByDateClaimExclude", query = "SELECT b  FROM CrlGeneralBank b , CrlGeneral1c c where b.policyId.id not in ( select a.idLink.clPolicyId from CsAccidents a) AND c.id = b.related1cGenId AND c.claim=1 AND c.claimDate>=:paidDate order by b.taxcode, b.customerPassport")
})
public class CrlGeneralBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TAXCODE")
    private String taxcode;
    @Column(name = "ISSUE_DATE")
    private String issueDate;
    @Column(name = "MATURE_DATE")
    private String matureDate;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "CUSTOMER_FULL_NAME")
    private String customerFullName;
    @Column(name = "CUSTOMER_DOB")
    private String customerDob;
    @Column(name = "CUSTOMER_ADDRESS")
    private String customerAddress;
    @Column(name = "CUSTOMER_SEX")
    private Character customerSex;
    @Column(name = "CUSTOMER_PASSPORT")
    private String customerPassport;
    @Column(name = "BANK_BRANCH_CODE")
    private String bankBranchCode;
    @Column(name = "INSURANT_ID")
    private String insurantId;
    @Column(name = "INSURANT_FULL_NAME")
    private String insurantFullName;
    @Column(name = "INSURANT_ADDRESS")
    private String insurantAddress;
    @Column(name = "INSURANT_PASSPORT")
    private String insurantPassport;
    @Column(name = "INSURANT_DOB")
    private String insurantDob;
    @Column(name = "CITIZENSHIP")
    private String citizenship;

    @JoinColumn(name = "POLICY_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private CrlPolicy policyId;

    @JoinColumn(name = "TP_ID", referencedColumnName = "TP_ID")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private CrlTariffPlan tpId;
    @JoinColumn(name = "RELATED_1C_GEN_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private CrlGeneral1c related1cGenId;


    /*
     @JoinColumn(name = "IMPORT_ID", referencedColumnName = "ID")
     @OneToOne(optional = false, fetch = FetchType.LAZY)
     private CrlDataImport importId;

     @JoinColumn(name = "POLICY_ID", referencedColumnName = "ID")
     @OneToOne(optional = false, fetch = FetchType.LAZY)
     private CrlPolicy policyId;


     */
    public String getCustomerPassport() {
        if (customerPassport == null) {
            return "";
        } else {
            return customerPassport;
        }
    }

    public String getCustomerFullName() {
        if (customerFullName == null) {
            return "";
        } else {
            return customerFullName.trim();
        }
    }

}
