/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.entity.crs;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author NPopov
 */
@Entity
//@IdClass(CrsfRsbPk.class)
@Data
@Table(name = "CRSFRSB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsfRsb.findAll", query = "SELECT g FROM CrsfRsb g")
})

public class CrsfRsb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@Generated(GenerationTime.NEVER)
    @Column(name = "RSBCLMNO")
    private String rsbclmno;
    @Id
    @Column(name = "RSBPOLNO")
    private String rsbpolno;
    @Id
    @Column(name = "RSBPLCD")
    private String rsbplcd;

    @Column(name = "RSBRIDNT")
    private String rsbridnt;

    @Column(name = "RSBRTYPE")
    private String rsbrtype;

    @Column(name = "RSBRDUPL")
    private String rsbrdupl;

    @Column(name = "RSBSTAT")
    private String rsbstat;

    @Column(name = "RSBSTATDTE")
    private String rsbstatdte;

    @Column(name = "RSBFAOK")
    private String rsbfaok;

    @Column(name = "RSBEXGRAC")
    private String rsbexgrac;

    @Column(name = "RSBCBST")
    private String rsbcbst;

    @Column(name = "RSBPLOB")
    private String rsbplob;

    @Column(name = "RSBPLWC")
    private String rsbplwc;

    @Column(name = "RSBHSCP")
    private String rsbhscp;

    @Column(name = "RSBHSCFR")
    private String rsbhscfr;

    @Column(name = "RSBHSCTO")
    private String rsbhscto;

    @Column(name = "RSBHSCFR2")
    private String rsbhscfr2;

    @Column(name = "RSBHSCTO2")
    private String rsbhscto2;

    @Column(name = "RSBHSCFR3")
    private String rsbhscfr3;

    @Column(name = "RSBHSCTO3")
    private String rsbhscto3;

    @Column(name = "RSBEFFDTE")
    private String rsbefedte;

    @Column(name = "RSBMATDTE")
    private String rsbmatdte;

    @Column(name = "RSBPYUDTE")
    private String rsbpyudte;

    @Column(name = "RSBREL")
    private String rsbrel;

    @Column(name = "RSBSEX")
    private String rsbsex;

    @Column(name = "RSBINSNAME")
    private String rsbinsname;

    @Column(name = "RSBINSBTHD")
    private String rsbinsbthd;

    @Column(name = "RSBISSAGE")
    private Integer rsbissage;

    @Column(name = "RSBANNPRM")
    private BigDecimal rsbannprm;

    @Column(name = "RSBMODPRM")
    private BigDecimal rsbmodprm;

    @Column(name = "RSBHSCPRM")
    private BigDecimal rsbhscprm;

    @Column(name = "RSBCOVAMT")
    private BigDecimal rsbcovamt;

    @Column(name = "RSBRESTYP")
    private String rsbrestyp;

    @Column(name = "RSBRESPCT")
    private BigDecimal rsbrespct;

    @Column(name = "RSBRESAMT")
    private BigDecimal rsbresamt;

    @Column(name = "RSBRESNEW")
    private BigDecimal rsbresnew;

    @Column(name = "RSBBFTPCT")
    private BigDecimal rsbbftpct;

    @Column(name = "RSBBFTAMT")
    private BigDecimal rsbbftamt;

    @Column(name = "RSBBFTTOT")
    private BigDecimal rsbbfttot;

    @Column(name = "RSBPSETTLE")
    private String rsbpsettle;

    @Column(name = "RSBDCLOBJ")
    private String rsbdclobj;

    @Column(name = "RSBDCLP2S")
    private BigDecimal rsbdclp2s;

    @Column(name = "RSBHISLSQN")
    private String rsbhislsqn;

    @Column(name = "RSBRECUSR")
    private String rsbrecusr;

    @Column(name = "RSBRECDTE")
    private String rsbrecdte;

    @JoinColumn(name = "RSBCLMNO", referencedColumnName = "CLMNO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CrsfPol crsfPol;
    public String toString() {
        return rsbclmno+"_"+rsbpolno+"_"+rsbplcd;
    }

}
 