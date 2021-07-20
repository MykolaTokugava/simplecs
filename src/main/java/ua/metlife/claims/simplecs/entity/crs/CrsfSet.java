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
@Data
@Table(name = "CRSFSET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsfSet.findAll", query = "SELECT g FROM CrsfSet g")
})

public class CrsfSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "SETCLMNO")
    private String setclmno;
    @Id
    @Column(name = "SETSETNO")
    private String setsetno;

    @Column(name = "SETPOLNO")
    private String setpolno;

    @Column(name = "SETSTATE")
    private String setstate;

    @Column(name = "SETCRTDATE")
    private String setcrtdate;

    @Column(name = "SETAPRDATE")
    private String setaprdate;

    @Column(name = "SETAPRUSER")
    private String setapruser;

    @Column(name = "SETPAYDATE")
    private String setpaydate;
    
    @Column(name = "SETREJDATE")
    private String setrejdate;

    @Column(name = "SETSETLED")
    private BigDecimal setsetled;

    @Column(name = "SETTBPAID")
    private BigDecimal settbpaid;

    @Column(name = "SETPINTOT")
    private BigDecimal setpintot;

    @Column(name = "SETPINPCT")
    private BigDecimal setpinpct;

    @Column(name = "SETPINLSQN")
    private String setpinlsqn;

    @Column(name = "SETSACLSQN")
    private String setsaclsqn;

    @Column(name = "SETNOTES")
    private String setnotes;

    @Column(name = "SETRECUSR")
    private String setrecusr;

    @Column(name = "SETRECDTE")
    private String setrecdte;

    @Column(name = "SETCURRNO")
    private String setcurrno;

 
    public String toString() {
        return "";
    }

}
 