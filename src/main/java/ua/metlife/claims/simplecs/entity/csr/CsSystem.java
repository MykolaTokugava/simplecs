/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.csr;

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
@Table(name = "CS_SYSTEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsSystem.findAll", query = "SELECT c FROM CsSystem c"),
    @NamedQuery(name = "CsSystem.findById", query = "SELECT c FROM CsSystem c WHERE c.id = :id"),
    @NamedQuery(name = "CsSystem.findBySystemName", query = "SELECT c FROM CsSystem c WHERE c.systemName = :systemName"),
    @NamedQuery(name = "CsSystem.findBySystemInfo", query = "SELECT c FROM CsSystem c WHERE c.systemInfo = :systemInfo"),
    @NamedQuery(name = "CsSystem.findBySystemCode", query = "SELECT c FROM CsSystem c WHERE c.systemCode = :systemCode"),
    @NamedQuery(name = "CsSystem.findBySystemType", query = "SELECT c FROM CsSystem c WHERE c.systemType = :systemType")})
public class CsSystem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "SYSTEM_NAME")
    private String systemName;
    @Basic(optional = false)
    @Column(name = "SYSTEM_INFO")
    private String systemInfo;
    @Basic(optional = false)
    @Column(name = "SYSTEM_CODE")
    private String systemCode;
    @Basic(optional = false)
    @Column(name = "SYSTEM_TYPE")
    private String systemType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSystem")
    private List<CsRegister> csRegisterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dataSource")
    private List<CsRegister> csRegisterList1;

    public CsSystem() {
    }

    public CsSystem(Integer id) {
        this.id = id;
    }

    public CsSystem(Integer id, String systemName, String systemInfo, String systemCode, String systemType) {
        this.id = id;
        this.systemName = systemName;
        this.systemInfo = systemInfo;
        this.systemCode = systemCode;
        this.systemType = systemType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(String systemInfo) {
        this.systemInfo = systemInfo;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    @XmlTransient
    public List<CsRegister> getCsRegisterList() {
        return csRegisterList;
    }

    public void setCsRegisterList(List<CsRegister> csRegisterList) {
        this.csRegisterList = csRegisterList;
    }

    @XmlTransient
    public List<CsRegister> getCsRegisterList1() {
        return csRegisterList1;
    }

    public void setCsRegisterList1(List<CsRegister> csRegisterList1) {
        this.csRegisterList1 = csRegisterList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CsSystem)) {
            return false;
        }
        CsSystem other = (CsSystem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metlife.cr.jpa.entity.CsSystem[ id=" + id + " ]";
    }

}
