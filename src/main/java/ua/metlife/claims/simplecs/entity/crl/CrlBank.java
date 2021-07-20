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
@Table(name = "CRL_BANK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlBank.findAll", query = "SELECT c FROM CrlBank c")})
public class CrlBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BANK_ID")
    private Integer bankId;
    @Basic(optional = false)
    @Column(name = "BANK_NAME")
    private String bankName;
    @Basic(optional = false)
    @Column(name = "BANK_CODE")
    private String bankCode;
    @Column(name = "BANK_DESC")
    private String bankDesc;
    @Lob
    @Column(name = "LOGO")
    private Serializable logo;
    @Column(name = "OLD_ID")
    private Integer oldId;
    @Column(name = "OLAS_ID")
    private String olasId;
    @Column(name = "COUNTRYOFREGISTRATION")
    private String countryofregistration;
    @Column(name = "COUNTRYOFOPERATION")
    private String countryofoperation;
    @Column(name = "EDRPOU")
    private String edrpou;
    @Column(name = "RISKLEVEL")
    private String risklevel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bpBankId")
    private List<CrlBankProduct> crlBankProductList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankId")
    private List<CrlBankContract> crlBankContractList;

    public CrlBank() {
    }

    public CrlBank(Integer bankId) {
        this.bankId = bankId;
    }

    public CrlBank(Integer bankId, String bankName, String bankCode) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankDesc() {
        return bankDesc;
    }

    public void setBankDesc(String bankDesc) {
        this.bankDesc = bankDesc;
    }

    public Serializable getLogo() {
        return logo;
    }

    public void setLogo(Serializable logo) {
        this.logo = logo;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public String getOlasId() {
        return olasId;
    }

    public void setOlasId(String olasId) {
        this.olasId = olasId;
    }

    public String getCountryofregistration() {
        return countryofregistration;
    }

    public void setCountryofregistration(String countryofregistration) {
        this.countryofregistration = countryofregistration;
    }

    public String getCountryofoperation() {
        return countryofoperation;
    }

    public void setCountryofoperation(String countryofoperation) {
        this.countryofoperation = countryofoperation;
    }

    public String getEdrpou() {
        return edrpou;
    }

    public void setEdrpou(String edrpou) {
        this.edrpou = edrpou;
    }

    public String getRisklevel() {
        return risklevel;
    }

    public void setRisklevel(String risklevel) {
        this.risklevel = risklevel;
    }

    @XmlTransient
    public List<CrlBankProduct> getCrlBankProductList() {
        return crlBankProductList;
    }

    public void setCrlBankProductList(List<CrlBankProduct> crlBankProductList) {
        this.crlBankProductList = crlBankProductList;
    }

    @XmlTransient
    public List<CrlBankContract> getCrlBankContractList() {
        return crlBankContractList;
    }

    public void setCrlBankContractList(List<CrlBankContract> crlBankContractList) {
        this.crlBankContractList = crlBankContractList;
    }

}
