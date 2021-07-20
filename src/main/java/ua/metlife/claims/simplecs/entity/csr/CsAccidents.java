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
@Table(name = "CS_ACCIDENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsAccidents.findAll", query = "SELECT c FROM CsAccidents c"),
    @NamedQuery(name = "CsAccidents.findById", query = "SELECT c FROM CsAccidents c WHERE c.id = :id"),
    @NamedQuery(name = "CsAccidents.findByDeath", query = "SELECT c FROM CsAccidents c WHERE c.death = :death")})
public class CsAccidents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "ID_LINK", referencedColumnName = "ID")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private CsIdLink idLink;

    @Basic(optional = false)
    @Column(name = "REASON_TYPE")
    private String ReasonType;
    @Basic(optional = false)
    @Column(name = "REASON_INFO")
    private String reasonInfo;
    @Column(name = "CLAIM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDate;
    @Column(name = "ADD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;
    @Column(name = "NOTIFICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notificationDate;
    @Basic(optional = false)
    @Column(name = "ADD_LDAP_LOGIN")
    private String addLdapLogin;
    @Basic(optional = false)
    @Column(name = "EXPORT_LDAP_LOGIN")
    private String exportLdapLogin;
    @Basic(optional = false)
    @Column(name = "EXPORT_STATUS")
    private String exportStatus;
    @Column(name = "EXPORT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exportDate;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Column(name = "DEATH")
    private int death;

}
