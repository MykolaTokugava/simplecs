/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.csr;

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
@Table(name = "CS_ID_LINK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsIdLink.findAll", query = "SELECT c FROM CsAccidents c"),
    @NamedQuery(name = "CsIdLink.findById", query = "SELECT c FROM CsAccidents c WHERE c.id = :id")
})
public class CsIdLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ID_PERSONAL")
    private Integer idPersonal;
    @Basic(optional = false)
    @Column(name = "ID_SYSTEM")
    private int idSystem;
    @Basic(optional = false)
    @Column(name = "ID_REGISTER")
    private int idRegister;
    @Basic(optional = false)
    @Column(name = "EID")
    private int eid;
    @Basic(optional = false)
    @Column(name = "G_CONTRACT")
    private Integer gContractId;
    @Basic(optional = false)
    @Column(name = "G_USER_ID")
    private Integer gUserId;
    @Basic(optional = false)
    @Column(name = "CLAIM_CLMNO")
    private String claimNumber;
    @Basic(optional = false)
    @Column(name = "CLAIM_POLNO")
    private String claimPolNumber;
    @Basic(optional = false)
    @Column(name = "OLASS_PN")
    private String olassPolicyNumber;
    @Basic(optional = false)
    @Column(name = "OLASS_AN")
    private String olassApplicationNumber;
    @Basic(optional = false)
    @Column(name = "C_POLICY_ID")
    private Integer clPolicyId;
    @Basic(optional = false)
    @Column(name = "C_TP_ID")
    private Integer clTpId;
    @Basic(optional = false)
    @Column(name = "C_TAXCODE")
    private String taxcode;

}
