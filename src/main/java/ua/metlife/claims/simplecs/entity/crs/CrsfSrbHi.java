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

/**
 *
 * @author NPopov
 */
@Entity
//@IdClass(CrsfRsbHiPk.class)
@Data
@Table(name = "CRSFRSBHI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsfSrbHi.findAll", query = "SELECT g FROM CrsfSrbHi g")
})

public class CrsfSrbHi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HSRBCLMNO")
    private String hsrbclmno;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HSRBSETNO")
    private String hsrbsetno;       
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HSRBPOLNO")
    private String hrsbpolno;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HSRBRPLAN")
    private String hsrbrplan;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HSRBSEQNO")
    private String hsrbseqno;         
    
    @Column(name = "HSRBSRCTP")
    private String hsrbsrctp;

    @Column(name = "HSRBSRCID")
    private String hsrbsrcid;

    @Column(name = "HSRBRDUPL")
    private String hsrbrdupl;

    @Column(name = "HSRBACTION")
    private String hsrbaction;

    @Column(name = "HSRBACTDTE")
    private String hsrbactdte;

    @Column(name = "HSRBACTDSC")
    private String hsrbactdsc;

    @Column(name = "HSRBTRSTAT")
    private String hsrbtrstat;

    @Column(name = "HSRBTRSTDT")
    private String hsrbtrstdt;

    @Column(name = "HSRBTRCODE")
    private String hsrbtrcode;

    @Column(name = "HSRBTRDATE")
    private String hsrbtrdate;

    @Column(name = "HSRBTRACCR")
    private String hsrbtraccr;

    @Column(name = "HSRBTRACDR")
    private String hsrbtracdr;

    @Column(name = "HSRBTRAMNT")
    private BigDecimal hsrbtramnt;

    @Column(name = "HSRBRECUSR")
    private String hsrbrecusr;

    @Column(name = "HSRBRECDTE")
    private String hsrbrecdte;

    public String toString() {
        return "";
    }

}
