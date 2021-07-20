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
@Table(name = "CRL_POLICY")
@XmlRootElement
@Data
@NamedQueries({
    @NamedQuery(name = "CrlPolicy.findAll", query = "SELECT c FROM CrlPolicy c")})
public class CrlPolicy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "VALID_NO")
    private String validNo;
    @Column(name = "INVALID_NO")
    private String invalidNo;
    @Column(name = "VALID_NO_CLEANED")
    private String validNoCleaned;
    @Column(name = "INVALID_NO_CLEANED")
    private String invalidNoCleaned;
    /*
     @OneToMany(mappedBy = "policyId")
     private List<CrlGeneral1c> crlGeneral1cList;
     @OneToMany(mappedBy = "policyId")
     private List<CrlGeneralBank> crlGeneralBankList;
     */
}
