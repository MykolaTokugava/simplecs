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

/**
 *
 * @author NPopov
 */
@Entity
@IdClass(CrsFclmHiPk.class)
@Data
@Table(name = "CRSFCLMHI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrsFclmHi.findAll", query = "SELECT g FROM CrsFclmHi g")
})

public class CrsFclmHi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Generated(GenerationTime.NEVER)
    @Column(name = "CLMNO")
    private String clmno;
    @Id
    @Column(name = "SEQNO")
    private String seqno;

    @Column(name = "RSTC")
    private String rstc;

    @Column(name = "RSTCDT")
    private String rstcdt;

    @Column(name = "ACTION")
    private String action;

    @Column(name = "ACTDSC")
    private String actdsc;

    @Column(name = "ACTDTE")
    private String actdte;    

    @Column(name = "RECUSR")
    private String recusr;

    @Column(name = "RECDTE")
    private String recdte;

    public String toString() {
        return clmno + " > " + seqno.trim();
    }

}
