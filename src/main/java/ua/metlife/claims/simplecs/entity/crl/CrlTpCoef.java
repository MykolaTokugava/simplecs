/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author NPopov
 */
@Entity
@Table(name = "CRL_TP_COEF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrlTpCoef.findAll", query = "SELECT c FROM CrlTpCoef c")})
public class CrlTpCoef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COEF_VALUE")
    private BigDecimal coefValue;
    @Column(name = "FIXED_VALUE")
    private BigDecimal fixedValue;
    @Column(name = "FIXED_VALUE_COEF")
    private BigDecimal fixedValueCoef;
    @JoinColumn(name = "IP_ID", referencedColumnName = "IP_ID")
    @ManyToOne(optional = false)
    private CrlInsurProduct ipId;
    @JoinColumn(name = "TP_ID", referencedColumnName = "TP_ID")
    @ManyToOne(optional = false)
    private CrlTariffPlan tpId;

    public CrlTpCoef() {
    }

    public CrlTpCoef(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCoefValue() {
        return coefValue;
    }

    public void setCoefValue(BigDecimal coefValue) {
        this.coefValue = coefValue;
    }

    public BigDecimal getFixedValue() {
        return fixedValue;
    }

    public void setFixedValue(BigDecimal fixedValue) {
        this.fixedValue = fixedValue;
    }

    public BigDecimal getFixedValueCoef() {
        return fixedValueCoef;
    }

    public void setFixedValueCoef(BigDecimal fixedValueCoef) {
        this.fixedValueCoef = fixedValueCoef;
    }

    public CrlInsurProduct getIpId() {
        return ipId;
    }

    public void setIpId(CrlInsurProduct ipId) {
        this.ipId = ipId;
    }

    public CrlTariffPlan getTpId() {
        return tpId;
    }

    public void setTpId(CrlTariffPlan tpId) {
        this.tpId = tpId;
    }

}
