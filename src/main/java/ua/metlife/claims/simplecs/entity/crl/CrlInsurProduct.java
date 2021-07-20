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
import java.util.List;

/**
 *
 * @author NPopov
 */
@Entity
@Table(name = "CRL_INSUR_PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlInsurProduct.findAll", query = "SELECT c FROM CrlInsurProduct c")})
public class CrlInsurProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IP_ID")
    private Integer ipId;
    @Basic(optional = false)
    @Column(name = "IP_NAME")
    private String ipName;
    @Column(name = "IP_CODE")
    private String ipCode;
    @Column(name = "IP_DESC")
    private String ipDesc;
    @Column(name = "PRODUCT_GROUP")
    private String productGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ipId")
    private List<CrlBpIp> crlBpIpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ipId")
    private List<CrlTpCoef> crlTpCoefList;

    public CrlInsurProduct() {
    }

    public CrlInsurProduct(Integer ipId) {
        this.ipId = ipId;
    }

    public CrlInsurProduct(Integer ipId, String ipName) {
        this.ipId = ipId;
        this.ipName = ipName;
    }

    public Integer getIpId() {
        return ipId;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
    }

    public String getIpName() {
        return ipName;
    }

    public void setIpName(String ipName) {
        this.ipName = ipName;
    }

    public String getIpCode() {
        return ipCode;
    }

    public void setIpCode(String ipCode) {
        this.ipCode = ipCode;
    }

    public String getIpDesc() {
        return ipDesc;
    }

    public void setIpDesc(String ipDesc) {
        this.ipDesc = ipDesc;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    @XmlTransient
    public List<CrlBpIp> getCrlBpIpList() {
        return crlBpIpList;
    }

    public void setCrlBpIpList(List<CrlBpIp> crlBpIpList) {
        this.crlBpIpList = crlBpIpList;
    }

    @XmlTransient
    public List<CrlTpCoef> getCrlTpCoefList() {
        return crlTpCoefList;
    }

    public void setCrlTpCoefList(List<CrlTpCoef> crlTpCoefList) {
        this.crlTpCoefList = crlTpCoefList;
    }

}
