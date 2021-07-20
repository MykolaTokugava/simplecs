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
@Table(name = "CRL_BANK_CONTRACT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlBankContract.findAll", query = "SELECT c FROM CrlBankContract c")})
public class CrlBankContract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CONTRACT_NO")
    private String contractNo;
    @JoinColumn(name = "BANK_ID", referencedColumnName = "BANK_ID")
    @ManyToOne(optional = false)
    private CrlBank bankId;

    public CrlBankContract() {
    }

    public CrlBankContract(Integer id) {
        this.id = id;
    }

    public CrlBankContract(Integer id, String contractNo) {
        this.id = id;
        this.contractNo = contractNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public CrlBank getBankId() {
        return bankId;
    }

    public void setBankId(CrlBank bankId) {
        this.bankId = bankId;
    }

}
