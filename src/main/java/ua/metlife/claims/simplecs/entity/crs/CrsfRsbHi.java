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
    @NamedQuery(name = "CrsfRsbHi.findAll", query = "SELECT g FROM CrsfRsbHi g")
})

public class CrsfRsbHi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HRSBCLMNO")
    private String hrsbclmno;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HRSBPOLNO")
    private String hrsbpolno;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HRSBRPLAN")
    private String hrsbsrplan;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "HRSBSEQNO")
    private String hrsbseqno;

    @Column(name = "HRSBSRCTP")
    private String hrsbsrctp;

    @Column(name = "HRSBSRCID")
    private String hrsbsrcid;

    @Column(name = "HRSBRDUPL")
    private String hrsbrdupl;

    @Column(name = "HRSBACTION")
    private String hrsbaction;

    @Column(name = "HRSBACTDTE")
    private String hrsbactdte;

    @Column(name = "HRSBACTDSC")
    private String hrsbactdsc;

    @Column(name = "HRSBTRSTAT")
    private String hrsbtrstat;

    @Column(name = "HRSBTRSTDT")
    private String hrsbtrstdt;

    @Column(name = "HRSBTRCODE")
    private String hrsbtrcode;

    @Column(name = "HRSBTRDATE")
    private String hrsbtrdate;

    @Column(name = "HRSBTRACN1")
    private String hrsbtracn1;

    @Column(name = "HRSBTRACN2")
    private String hrsbtracn2;

    @Column(name = "HRSBTRAMNT")
    private BigDecimal hrsbtramnt;

    @Column(name = "HRSBRECUSR")
    private String hrsbrecusr;

    @Column(name = "HRSBRECDTE")
    private String hrsbrecdte;

    public String toString() {
        return hrsbclmno + "_" + hrsbpolno + "_" + hrsbsrplan;
    }

}
