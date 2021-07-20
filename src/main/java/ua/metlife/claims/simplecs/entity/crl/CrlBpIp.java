/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author NPopov
 */
@Entity
@Table(name = "CRL_BP_IP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlBpIp.findAll", query = "SELECT c FROM CrlBpIp c")})
public class CrlBpIp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "IP_ID", referencedColumnName = "IP_ID")
    @ManyToOne(optional = false)
    private CrlInsurProduct ipId;
    @JoinColumn(name = "BP_ID", referencedColumnName = "BP_ID")
    @ManyToOne(optional = false)
    private CrlBankProduct bpId;

    public CrlBpIp() {
    }

    public CrlBpIp(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CrlInsurProduct getIpId() {
        return ipId;
    }

    public void setIpId(CrlInsurProduct ipId) {
        this.ipId = ipId;
    }

    public CrlBankProduct getBpId() {
        return bpId;
    }

    public void setBpId(CrlBankProduct bpId) {
        this.bpId = bpId;
    }

}
