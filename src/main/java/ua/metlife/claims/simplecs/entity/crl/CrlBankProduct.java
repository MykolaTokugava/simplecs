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

/**
 *
 * @author NPopov
 */
@Entity
@Data
@Table(name = "CRL_BANK_PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlBankProduct.findAll", query = "SELECT c FROM CrlBankProduct c")})
public class CrlBankProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BP_ID")
    private Integer bpId;
    @Basic(optional = false)
    @Column(name = "BP_NAME")
    private String bpName;
    @Column(name = "BP_VALID_FROM")
    private String bpValidFrom;
    @Column(name = "BP_VALID_TO")
    private String bpValidTo;
    @Column(name = "BP_DESC")
    private String bpDesc;
    @Column(name = "OLD_ID")
    private String oldId;
    @Column(name = "TP_IS_DYNAMIC")
    private Integer tpIsDynamic;
    @JoinColumn(name = "BP_BANK_ID", referencedColumnName = "BANK_ID")
    @ManyToOne(optional = false)
    private CrlBank bpBankId;

}
