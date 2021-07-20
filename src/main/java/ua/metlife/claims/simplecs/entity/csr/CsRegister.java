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
import java.util.Date;

/**
 *
 * @author NPopov
 */
@Entity
@Data
@Table(name = "CS_REGISTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsRegister.findAll", query = "SELECT c FROM CsRegister c"),
    @NamedQuery(name = "CsRegister.findById", query = "SELECT c FROM CsRegister c WHERE c.id = :id"),
    @NamedQuery(name = "CsRegister.findByEid", query = "SELECT c FROM CsRegister c WHERE c.eid = :eid")})
public class CsRegister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "PATRO_NAME")
    private String patroName;
    @Basic(optional = false)
    @Column(name = "INFORMER")
    private String informer;
    @Basic(optional = false)
    @Column(name = "CONTACT_PHONES")
    private String contactPhones;
    @Basic(optional = false)
    @Column(name = "CONTACT_EMAIL")
    private String contactEmail;
    @Basic(optional = false)
    @Column(name = "REASON_TYPE")
    private String ReasonType;
    @Basic(optional = false)
    @Column(name = "REASON_INFO")
    private String reasonInfo;
    @Basic(optional = false)
    @Column(name = "DEATH")
    private int death;
    @Column(name = "CLAIM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDate;
    @Column(name = "ADD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;
    @Basic(optional = false)
    @Column(name = "ADD_LDAP_LOGIN")
    private String addLdapLogin;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Column(name = "EID")
    private int eid;
    @Column(name = "ID_SYSTEM")
    private Integer idSystem;
    @Column(name = "DATA_SOURCE")
    private Integer dataSource;
    @Column(name = "IS_INSURED")
    private Integer isInsured;

}
