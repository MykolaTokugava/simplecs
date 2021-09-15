/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crs;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author NPopov
 */
@Entity
@Data
//@IdClass(CrsfPolPk.class)
@Table(name = "CRSFPOL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsfPol.findAll", query = "SELECT g FROM CrsfPol g"),
    @NamedQuery(name = "CrsfPol.findById", query = "SELECT g FROM CrsfPol g where g.clmno=:clmNumber")})

public class CrsfPol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "CLMNO")
    private String clmno;

    @Column(name = "POLNO")
    private String polno;

    @Column(name = "CLMON")
    private String clmon;

    @Column(name = "CRFID")
    private String crfid;

    @Column(name = "STAT")
    private String stat;

    @Column(name = "STATDTE")
    private String statdte;

    @Column(name = "FAOK")
    private String faok;

    @Column(name = "RAOK")
    private String raok;

    @Column(name = "BNKASGN")
    private String bnkasgn;

    @Column(name = "INAME")
    private String iname;

    @Column(name = "IBTHD")
    private String ibthd;

    @Column(name = "IIDNO")
    private String iidno;

    @Column(name = "ONAME")
    private String oname;

    @Column(name = "OBTHD")
    private String obthd;

    @Column(name = "CPYFR")
    private String cpyfr;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "PLOB")
    private String plob;

    @Column(name = "ISSDTE")
    private String issdte;

    @Column(name = "EFFDTE")
    private String effdte;

    @Column(name = "PAYDTE")
    private String paydte;

    @Column(name = "CASTAT")
    private String castat;

    @Column(name = "BILMOD")
    private String bilmod;

    @Column(name = "MODPRM")
    private BigDecimal modprm;

    @Column(name = "ANNPRM")
    private BigDecimal annprm;

    @Column(name = "WCPPRM")
    private BigDecimal wcpprm;

    @Column(name = "COVAMT")
    private BigDecimal covamt;

    @Column(name = "RESAMT")
    private BigDecimal resamt;

    @Column(name = "BFTAMT")
    private BigDecimal bftamt;

    @Column(name = "BFTTOT")
    private BigDecimal bfttot;

    @Column(name = "CCAG1FC")
    private String ccag1fc;

    @Column(name = "EAAG1NM")
    private String eaag1nm;

    @Column(name = "CCAG2FC")
    private String ccag2fc;

    @Column(name = "EAAG2NM")
    private String eaag2nm;

    @Column(name = "DCLOBJ")
    private String dclobj;

    @Column(name = "DCLP2S")
    private BigDecimal dclp2s;

    @Column(name = "RECUSR")
    private String recusr;

    @Column(name = "RECDTE")
    private String recdte;

    @JoinColumn(name = "CLMNO", referencedColumnName = "CLMNO", insertable = false, updatable = false, nullable = true)
    @OneToOne(optional = true)
    private CrsfClm crsfClm;

    @JoinColumn(name = "RSBCLMNO", referencedColumnName = "CLMNO", insertable = false, updatable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CrsfRsb> ridersRsbList;


    @JoinColumn(name = "CLMNO", referencedColumnName = "CLMNO", insertable = false, updatable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CrsFclmHi> claimHistory;


    @JoinColumn(name = "HRSBCLMNO", referencedColumnName = "CLMNO", insertable = false, updatable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CrsfRsbHi> claimRsbHistory;

    @JoinColumn(name = "SETCLMNO", referencedColumnName = "CLMNO", insertable = false, updatable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CrsfSet> setList;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    
}
