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
@Data
//@IdClass(CrsfClmPk.class)
@Table(name = "CRSFCLM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsfClm.findAll", query = "SELECT g FROM CrsfClm g"),
    @NamedQuery(name = "CrsfClm.findById", query = "SELECT g FROM CrsfClm g where g.clmno=:clmNumber"),
    @NamedQuery(name = "CrsfClm.findClaimsByDateClaim", query = "SELECT g FROM CrsfClm g where g.evtdt>=:paidDate order by g.iname")
})

public class CrsfClm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@Generated(GenerationTime.NEVER)
    @Column(name = "CLMNO")
    private String clmno;

    @Column(name = "CRFID")
    private String crfid;

    @Column(name = "CLMON")
    private String clmon;

    @Column(name = "STAT")
    private String stat;

    @Column(name = "STATDTE")
    private String statdte;

    @Column(name = "CHCK")
    private String chck;

    @Column(name = "FAOK")
    private String faok;

    @Column(name = "PAOK")
    private String paok;

    @Column(name = "CATEG")
    private String categ;

    @Column(name = "DEATH")
    private String death;

    @Column(name = "CAUSE")
    private String cause;

    @Column(name = "EVENT")
    private String event;

    @Column(name = "INAME")
    private String iname;

    @Column(name = "IBTHD")
    private String ibthd;

    @Column(name = "IIDNO")
    private String iidno;

    @Column(name = "IADR1")
    private String iadr1;

    @Column(name = "IADR2")
    private String iadr2;

    @Column(name = "IZIP")
    private String izip;

    @Column(name = "ICITY")
    private String icity;

    @Column(name = "EVTDT")
    private String evtdt;

    @Column(name = "NTFDT")
    private String ntfdt;

    @Column(name = "ENTDT")
    private String entdt;

    @Column(name = "COVAMT")
    private BigDecimal covamt;

    @Column(name = "RESAMT")
    private BigDecimal resamt;

    @Column(name = "BFTAMT")
    private BigDecimal bftamt;

    @Column(name = "BFTTOT")
    private BigDecimal bfttot;

    @Column(name = "DCLOBJ")
    private String dclobj;

    @Column(name = "DCLP2S")
    private BigDecimal dclp2s;

    @Column(name = "HCLLSQN") //history
    private String hcllsqn;

    @Column(name = "SETLSQN")
    private String setlsqn;

    @Column(name = "FLWLSQN")
    private String flwlsqn;

    @Column(name = "FMELSQN")
    private String fmelsqn;

    @Column(name = "FLELSQN")
    private String flelsqn;

    @Column(name = "SETPAID")
    private String setpaid;

    @Column(name = "SETPDTE")
    private String setpdte;

    @Column(name = "FMEAMT")
    private BigDecimal fmeamt;

    @Column(name = "FLEAMT")
    private BigDecimal fleamt;

    @Column(name = "RECUSR")
    private String recusr;

    @Column(name = "RECDTE")
    private String recdte;

    
    

}
